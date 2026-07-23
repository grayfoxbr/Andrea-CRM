package com.example.appauthbase.config

/**
 * CONFIGURAÇÃO DE REDE
 *
 * ⚠️  IMPORTANTE: Dispositivo físico conectado via USB NÃO usa 10.0.2.2!
 *
 * Como descobrir o IP da sua máquina:
 *   • Linux/Mac: rode `ip route get 8.8.8.8 | awk '{print $7}'` no terminal
 *   • Windows:   rode `ipconfig` e procure "IPv4 Address"
 *
 * Exemplo: se o IP da sua máquina for 192.168.1.100, defina:
 *   const val SERVER_HOST = "192.168.1.100"
 *
 * O dispositivo Android e a máquina precisam estar na MESMA rede Wi-Fi,
 * OU o servidor precisa estar acessível via USB (port-forward via adb).
 *
 * Alternativa com ADB port-forward (não precisa mudar o IP):
 *   Execute no terminal: adb reverse tcp:8080 tcp:8080
 *   Execute no terminal: adb reverse tcp:8081 tcp:8081
 *   Aí pode deixar SERVER_HOST = "localhost"
 */
object NetworkConfig {

    const val SERVER_HOST = "localhost"

    const val GATEWAY_PORT = 8082

    // Todo o tráfego do app passa pelo hermecard-gateway,
    // que roteia para o auth-api-server e o resource-api internamente.
    const val GATEWAY_BASE_URL = "http://$SERVER_HOST:$GATEWAY_PORT"

    // Endpoints OAuth2 (roteados pelo gateway para o auth-api-server)
    const val AUTHORIZE_URL = "$GATEWAY_BASE_URL/oauth2/authorize"
    const val TOKEN_URL = "$GATEWAY_BASE_URL/oauth2/token"

    const val CLIENT_ID = "meu-client"
    const val REDIRECT_URI = "com.example.appauthbase:/oauth2redirect"
    const val SCOPES = "openid profile email"
}