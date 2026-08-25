# Andrea CRM - Master Backend Orchestrator (PowerShell)
$Host.UI.RawUI.WindowTitle = "Andrea CRM - Backend Orchestrator"
Clear-Host

Write-Host "================================================================" -ForegroundColor Cyan
Write-Host "       🚀 ANDREA CRM - INICIALIZADOR DO BACK-END E BANCO" -ForegroundColor White
Write-Host "================================================================" -ForegroundColor Cyan
Write-Host ""

# 1. Iniciar Docker Compose
Write-Host "[1/4] Iniciando Postgres (5432) e Redis (6379) via Docker Compose..." -ForegroundColor Yellow
$dockerResult = docker compose up -d 2>&1
if ($LASTEXITCODE -ne 0) {
    Write-Host "[AVISO] Iniciando Docker Desktop..." -ForegroundColor Magenta
    Start-Process "C:\Program Files\Docker\Docker\Docker Desktop.exe"
    Write-Host "Aguardando 15 segundos para inicialização do Docker..." -ForegroundColor Gray
    Start-Sleep -Seconds 15
    docker compose up -d
}

Write-Host ""
# 2. Configurar ADB Reverse
Write-Host "[2/4] Configurando tunelamento ADB para celular/emulador (porta 8082)..." -ForegroundColor Yellow
adb reverse tcp:8082 tcp:8082 2>$null
if ($LASTEXITCODE -eq 0) {
    Write-Host "  -> [OK] Dispositivo Android conectado ao Gateway na porta 8082!" -ForegroundColor Green
} else {
    Write-Host "  -> [INFO] Dispositivo USB ainda não conectado (será mapeado na execução)." -ForegroundColor Gray
}

Write-Host ""
# 3. Iniciar as 3 APIs Spring Boot
Write-Host "[3/4] Inicializando Microsserviços Spring Boot..." -ForegroundColor Yellow

$crmRoot = Split-Path -Parent $MyInvocation.MyCommand.Path

# Auth API (8080)
Start-Process cmd.exe -ArgumentList "/k cd /d `"$crmRoot\andrea-auth-api-server`" && mvnw.cmd spring-boot:run"
Write-Host "  -> Auth Server iniciado na porta 8080" -ForegroundColor Cyan

# Resource API (8081)
Start-Process cmd.exe -ArgumentList "/k cd /d `"$crmRoot\andrea-resource-api`" && mvnw.cmd spring-boot:run"
Write-Host "  -> Resource API iniciada na porta 8081" -ForegroundColor Cyan

# Gateway (8082)
Start-Process cmd.exe -ArgumentList "/k cd /d `"$crmRoot\andrea-gateway`" && mvnw.cmd spring-boot:run"
Write-Host "  -> API Gateway iniciado na porta 8082" -ForegroundColor Cyan

Write-Host ""
Write-Host "================================================================" -ForegroundColor Green
Write-Host "   ✨ ECOSSISTEMA COMPLETO EM EXECUÇÃO!" -ForegroundColor White
Write-Host "================================================================" -ForegroundColor Green
Write-Host ""
Write-Host "   • PostgreSQL:       localhost:5432 (Banco: andreadb)" -ForegroundColor Gray
Write-Host "   • Redis Cache:      localhost:6379" -ForegroundColor Gray
Write-Host "   • Auth Server:      http://localhost:8080" -ForegroundColor Gray
Write-Host "   • Resource API:     http://localhost:8081" -ForegroundColor Gray
Write-Host "   • API Gateway:      http://localhost:8082 (Alvo do app mobile)" -ForegroundColor White
Write-Host "   • Web Preview:      http://localhost:8090" -ForegroundColor Cyan
Write-Host ""
Write-Host "Passo para rodar no celular/emulador:" -ForegroundColor Yellow
Write-Host "1. Abra o projeto no Android Studio" -ForegroundColor White
Write-Host "2. Conecte o celular USB ou inicie o Emulador" -ForegroundColor White
Write-Host "3. Clique no botão verde 'Run' (Shift + F10)" -ForegroundColor White
Write-Host ""
pause
