# Andrea-CRM - API Gateway & Authentication Server

Este projeto consiste em um ecossistema de autenticação e microsserviços composto por:

1. **andrea-crm-auth-api-server**: Um servidor de autorização OAuth2/OIDC baseado em Spring Security Authorization Server.
2. **andrea-crm-resource-api**: Uma API de recursos reativa baseada em Spring WebFlux.

---

## 🚀 Como o Redis está Sendo Utilizado? (Tutorial para o Raul)

Adicionamos suporte ao **Redis** no servidor de autenticação (`andrea-crm-auth-api-server`) para atuar em duas frentes fundamentais: **Sessões Distribuídas (Session State)** e **Cache de Alta Performance (Caching)**.

### 1. Sessões Distribuídas com Spring Session

Por padrão, o Spring Security armazena o contexto de autenticação do usuário (sessão HTTP do formulário de login) na memória da JVM. Se o servidor for reiniciado ou escalado horizontalmente, o usuário perde o login.

* **O que fizemos:** Integramos o `spring-session-data-redis`. Agora, todas as sessões ativas de login do formulário do Andrea-CRM são persistidas no Redis.

* **Benefício:** Permite reiniciar o servidor sem deslogar os usuários e facilita o balanceamento de carga entre múltiplas instâncias da API.

### 2. Cache de Performance (Spring Cache + Redis)

Para evitar chamadas redundantes e caras ao banco de dados PostgreSQL a cada requisição ou emissão de token, criamos a classe de configuração `RedisCacheConfig.java` e aplicamos cacheamento nos seguintes fluxos estratégicos:

#### A) Cache de Usuários (`CustomUserDetailsService`)

* **Método:** `loadUserByUsername(String username)`
* **Anotação:** `@Cacheable(value = "users", key = "#username")`
* **Como funciona:** Na primeira vez que o usuário faz login ou o token é validado, o Spring busca as credenciais no banco e as salva no Redis sob a chave `users::<username>` com um tempo de vida (TTL) padrão de 10 minutos. Nas requisições seguintes, as credenciais são lidas instantaneamente do Redis.

#### B) Cache de Claims OIDC (`OidcUserInfoService`)

* **Método:** `loadUser(String principalName)`
* **Anotação:** `@Cacheable(value = "oidcUserInfo", key = "#principalName")`
* **Como funciona:** O fluxo OpenID Connect (OIDC) requisita os detalhes de perfil do usuário logado (nome, e-mail verificado, etc.). Essas informações são cacheadas sob a chave `oidcUserInfo::<email>` para evitar queries repetitivas na tabela `users`.

#### C) Cache de Clientes OAuth2 (`JpaRegisteredClientRepository`)

* **Métodos:** `findById(String id)` e `findByClientId(String clientId)`
* **Anotações:** `@Cacheable` sob as namespaces `registeredClientsById` e `registeredClientsByClientId`.
* **Evicção Automática:** No método `save(RegisteredClient registeredClient)`, adicionamos `@Caching(evict = { ... })`. Isso garante que, se um cliente OAuth2 (ex.: o aplicativo mobile) tiver suas configurações atualizadas e salvas, o cache antigo é apagado do Redis no mesmo instante para evitar dados obsoletos.

---

## 🛠️ Como Rodar o Projeto com Docker

Criamos um arquivo `docker-compose.yml` na raiz do projeto que inicia todos os serviços de suporte necessários.

### Pré-requisitos

* Possuir o **Docker** e **Docker Compose** instalados (ex.: Docker Desktop).

### Passo a Passo

1. **Subir os containers (PostgreSQL e Redis):**

   Na raiz do projeto, execute:

   ```bash
   docker-compose up -d
   ```

   Isso iniciará:

   * **PostgreSQL** na porta `5432` com a base de dados `andrea_crm_db` já configurada.
   * **Redis** na porta `6379` para gerenciar sessões e cache.

2. **Compilar e testar a aplicação:**

   Os testes unitários foram configurados para ignorar o Redis e rodar em banco em memória (H2), o que significa que você pode compilar sem precisar do Redis em execução.

   ```bash
   cd andrea-crm-auth-api-server
   ./mvnw clean test
   ```

3. **Iniciar o Servidor de Autenticação:**

   ```bash
   ./mvnw spring-boot:run
   ```

---

## ⚙️ Arquivos Modificados e Criados

* **`docker-compose.yml`**: Orquestração do PostgreSQL e Redis.
* **`andrea-crm-auth-api-server/pom.xml`**: Inclusão das dependências do Redis (`spring-boot-starter-data-redis`, `spring-session-data-redis`) e correção do driver H2 para testes.
* **`andrea-crm-auth-api-server/src/main/resources/application.properties`**: Configuração das propriedades de conexão do Redis (`localhost:6379`) e definição do Redis como Session Store.
* **`andrea-crm-auth-api-server/src/test/resources/application.properties`**: Desabilitação de Session e Cache no ambiente de testes.
* **`andrea-crm-auth-api-server/src/main/java/com/angels/andrea_crm_auth_api_server/config/RedisCacheConfig.java`**: Configurações gerais e tempo de expiração do cache.
* **`andrea-crm-auth-api-server/src/main/java/com/angels/andrea_crm_auth_api_server/service/CustomUserDetailsService.java`**: Anotação de cacheamento dos detalhes do usuário.
* **`andrea-crm-auth-api-server/src/main/java/com/angels/andrea_crm_auth_api_server/service/OidcUserInfoService.java`**: Anotação de cacheamento do perfil OIDC.
* **`andrea-crm-auth-api-server/src/main/java/com/angels/andrea_crm_auth_api_server/repo/JpaRegisteredClientRepository.java`**: Anotação de leitura e expiração manual do cache de clientes.
