@echo off
title Andrea CRM - Servidor Back-end Completo
color 0B
chcp 65001 > nul

echo ================================================================
echo        🚀 ANDREA CRM - INICIALIZADOR DO BACK-END E BANCO
echo ================================================================
echo.

:: 1. Iniciar Docker Compose (Postgres + Redis)
echo [1/4] Iniciando containers Docker (Postgres na 5432 e Redis na 6379)...
docker compose up -d
if %errorlevel% neq 0 (
    echo.
    echo [AVISO] Certifique-se de que o aplicativo 'Docker Desktop' esta aberto!
    echo Tentando iniciar o Docker Desktop...
    start "" "C:\Program Files\Docker\Docker\Docker Desktop.exe"
    echo Aguardando 15 segundos para o Docker inicializar...
    timeout /t 15 /nobreak > nul
    docker compose up -d
)

echo.
:: 2. Configurar ADB Reverse para o Emulador / Celular
echo [2/4] Configurando tunelamento ADB (adb reverse tcp:8082 tcp:8082)...
adb reverse tcp:8082 tcp:8082 2>nul
if %errorlevel% equ 0 (
    echo [OK] Celular/Emulador conectado ao Gateway na porta 8082!
) else (
    echo [INFO] Nenhum dispositivo USB detectado no momento.
    echo        (Ao conectar o celular/iniciar o emulador, o Gradle configurara automaticamente).
)

echo.
:: 3. Iniciar as 3 APIs Spring Boot em novas janelas do terminal
echo [3/4] Iniciando APIs de Microsservicos:
echo        - Auth Server na porta 8080...
start "Andrea Auth Server (Porta 8080)" cmd /k "cd andrea-auth-api-server && mvnw.cmd spring-boot:run"

echo        - Resource API na porta 8081...
start "Andrea Resource API (Porta 8081)" cmd /k "cd andrea-resource-api && mvnw.cmd spring-boot:run"

echo        - Gateway na porta 8082...
start "Andrea API Gateway (Porta 8082)" cmd /k "cd andrea-gateway && mvnw.cmd spring-boot:run"

echo.
echo ================================================================
echo   ✨ ECOSSISTEMA INICIADO COM SUCESSO!
echo ================================================================
echo.
echo   • PostgreSQL:       localhost:5432 (Banco: andreadb)
echo   • Redis Cache:      localhost:6379
echo   • Auth Server:      http://localhost:8080
echo   • Resource API:     http://localhost:8081
echo   • API Gateway:      http://localhost:8082 (Alvo do app mobile)
echo   • Web Preview:      http://localhost:8090
echo.
echo   Para testar no celular/emulador:
echo   1. Conecte o celular USB ou abra o emulador no Android Studio
echo   2. Abra o Android Studio e clique no Play (Run 'app')
echo.
pause
