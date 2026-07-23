-- Client OAuth2 padrão usado pelo app Android (com.example.appauthbase)
INSERT INTO oauth2_registered_client (
    id, clientid, clientidissuedat, clientsecret,
    clientname, clientauthenticationmethods, authorizationgranttypes,
    redirecturis, postlogoutredirecturis, scopes,
    clientsettings, tokensettings
) VALUES (
             gen_random_uuid()::varchar,
             'meu-client',
             now(),
             NULL,
             'Meu Client',
             'none',
             'authorization_code,client_credentials,refresh_token',
             'http://localhost:8081/login/oauth2/code/meu-client,http://localhost:8081/callback,com.example.appauthbase:/oauth2redirect',
             'http://localhost:8081/logout',
             'openid,profile,email,read,write',
             '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":true,"settings.client.require-authorization-consent":false}',
             '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":false,"settings.token.x509-certificate-bound-access-tokens":false,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",604800.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000],"settings.token.device-code-time-to-live":["java.time.Duration",300.000000000]}'
         );

-- Usuário admin padrão
-- Senha em texto plano: 123456 (hash BCrypt abaixo)
INSERT INTO users (
    username, password, role, firstname, lastname, email,
    emailverified, enabled, accountlocked,
    createdat, updatedat,
    phonenumber, phoneverified, pictureurl, locale, zoneinfo
) VALUES (
             'admin',
             '$2b$10$KbX06G3u5ySG4.xCaQN7QeYc8/79Fuk5BMq.k5jX7PBw6bs.QQs/C',
             'ROLE_ADMIN',
             'Raul', 'Angels', 'admin@hermecard.com',
             TRUE, TRUE, FALSE,
             now(), now(),
             '+5511999999999', TRUE, 'https://i.pravatar.cc/300', 'pt-BR', 'America/Sao_Paulo'
         );