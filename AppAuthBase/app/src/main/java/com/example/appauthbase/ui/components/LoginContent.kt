package com.example.appauthbase.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appauthbase.presentation.AuthStateData
import com.example.appauthbase.theme.NeoBackground
import com.example.appauthbase.theme.NeoBlack
import com.example.appauthbase.theme.NeoCardWhite
import com.example.appauthbase.theme.NeoLime
import com.example.appauthbase.theme.NeoTextDark
import com.example.appauthbase.theme.NeoTextMuted
import com.example.appauthbase.theme.NeoYellow
import com.example.appauthbase.theme.AppAuthBaseTheme

@Preview(showBackground = true, name = "Logged Out")
@Composable
private fun LoginContentLoggedOutPreview() {
    AppAuthBaseTheme {
        LoginContent(
            uiState = AuthStateData(),
            onLoginClick = {},
            onRegisterClick = {},
            onLogoutClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Logged Out - Error")
@Composable
private fun LoginContentErrorPreview() {
    AppAuthBaseTheme {
        LoginContent(
            uiState = AuthStateData(errorMessage = "Credenciais inválidas ou sessão expirada."),
            onLoginClick = {},
            onRegisterClick = {},
            onLogoutClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Loading")
@Composable
private fun LoginContentLoadingPreview() {
    AppAuthBaseTheme {
        LoginContent(
            uiState = AuthStateData(isLoading = true),
            onLoginClick = {},
            onRegisterClick = {},
            onLogoutClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Logged In")
@Composable
private fun LoginContentLoggedInPreview() {
    AppAuthBaseTheme {
        LoginContent(
            uiState = AuthStateData(isLoggedIn = true, accessToken = "abc1234567890"),
            onLoginClick = {},
            onRegisterClick = {},
            onLogoutClick = {}
        )
    }
}

@Composable
fun LoginContent(
    uiState: AuthStateData,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(NeoBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.height(16.dp))

            // Neo-Brutalist Brand Logo
            BespokeLogo(isLarge = true)

            Spacer(Modifier.height(36.dp))

            // Neo-Brutalist Authentication Card
            LuxuryGlassCard(cornerRadius = 22.dp) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(26.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when {
                        uiState.isLoading -> {
                            LoginLoadingSection()
                        }
                        uiState.isLoggedIn -> {
                            LoggedInSection(onLogoutClick = onLogoutClick)
                        }
                        else -> {
                            LoggedOutSection(
                                errorMessage = uiState.errorMessage,
                                onLoginClick = onLoginClick,
                                onRegisterClick = onRegisterClick
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            // Security Badges
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NeoSecurityPill(icon = Icons.Default.Shield, text = "OAuth2.0 + PKCE")
                NeoSecurityPill(icon = Icons.Default.Speed, text = "Spring Gateway")
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun LoginLoadingSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = NeoBlack,
            modifier = Modifier.size(46.dp),
            strokeWidth = 3.5.dp
        )
        Spacer(Modifier.height(20.dp))
        Text(
            text = "Autenticando sessão segura...",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Black,
            color = NeoBlack
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Validando tokens com Andrea Auth Server",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = NeoTextMuted
        )
    }
}

@Composable
private fun LoggedInSection(onLogoutClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(NeoLime),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = NeoBlack,
                modifier = Modifier.size(36.dp)
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Sessão Ativa",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            color = NeoBlack
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "Credenciais autenticadas com sucesso.",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = NeoTextMuted,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(24.dp))

        AndreaOutlinedButton(
            text = "Encerrar Sessão",
            onClick = onLogoutClick
        )
    }
}

@Composable
private fun LoggedOutSection(
    errorMessage: String?,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Acesso Corporativo",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            color = NeoBlack
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = "Conecte-se para gerenciar sua carteira de clientes",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = NeoTextMuted
        )

        if (errorMessage != null) {
            Spacer(Modifier.height(16.dp))
            AndreaStatusBanner(message = errorMessage, type = BannerType.ERROR)
        }

        Spacer(Modifier.height(24.dp))

        AndreaPrimaryButton(
            text = "Entrar com OAuth 2.0 / SSO",
            icon = Icons.Default.Lock,
            onClick = onLoginClick
        )

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = NeoBlack,
                thickness = 1.5.dp
            )
            Text(
                text = "OU",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Black,
                color = NeoBlack,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = NeoBlack,
                thickness = 1.5.dp
            )
        }

        Spacer(Modifier.height(20.dp))

        AndreaOutlinedButton(
            text = "Criar nova conta",
            icon = Icons.Default.PersonAdd,
            onClick = onRegisterClick
        )
    }
}

@Composable
private fun NeoSecurityPill(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = NeoCardWhite,
        border = androidx.compose.foundation.BorderStroke(2.dp, NeoBlack),
        modifier = Modifier.padding(horizontal = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = NeoBlack,
                modifier = Modifier.size(14.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                color = NeoBlack,
                fontSize = 11.sp,
                fontWeight = FontWeight.Black
            )
        }
    }
}
