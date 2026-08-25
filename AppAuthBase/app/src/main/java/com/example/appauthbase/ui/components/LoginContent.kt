package com.example.appauthbase.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Security
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appauthbase.presentation.AuthStateData
import com.example.appauthbase.theme.AndreaAmbientGlowDark
import com.example.appauthbase.theme.AndreaAmbientGlowLight
import com.example.appauthbase.theme.AndreaElectricGradient
import com.example.appauthbase.theme.ElectricCyan
import com.example.appauthbase.theme.EmeraldPulse
import com.example.appauthbase.theme.ObsidianBorder
import com.example.appauthbase.theme.RosePulse
import com.example.appauthbase.theme.RoyalSapphire
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
    val isDark = isSystemInDarkTheme()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Ambient Radial Glow
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(if (isDark) AndreaAmbientGlowDark else AndreaAmbientGlowLight)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.height(12.dp))

            // Bespoke Geometric Monogram Logo
            BespokeLogo(isLarge = true)

            Spacer(Modifier.height(32.dp))

            // Main Glass Authentication Card
            LuxuryGlassCard(cornerRadius = 24.dp) {
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

            // Architecture Trust Badges
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SecurityPill(icon = Icons.Default.Shield, text = "OAuth2.0 + PKCE")
                SecurityPill(icon = Icons.Default.Speed, text = "Spring Gateway")
                SecurityPill(icon = Icons.Default.CheckCircle, text = "Redis Cache")
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
            color = ElectricCyan,
            modifier = Modifier.size(46.dp),
            strokeWidth = 3.dp
        )
        Spacer(Modifier.height(20.dp))
        Text(
            text = "Autenticando sessão segura...",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Validando tokens com Andrea Auth Server",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
                .background(EmeraldPulse.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = EmeraldPulse,
                modifier = Modifier.size(36.dp)
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Sessão Ativa",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "Credenciais autenticadas com sucesso.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
    val isDark = isSystemInDarkTheme()

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Acesso Corporativo",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = "Conecte-se com sua conta para gerenciar clientes",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
                color = if (isDark) ObsidianBorder else MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
            )
            Text(
                text = "OU",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = if (isDark) ObsidianBorder else MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
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
private fun SecurityPill(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {
    Surface(
        shape = RoundedCornerShape(50),
        color = ElectricCyan.copy(alpha = 0.08f),
        border = androidx.compose.foundation.BorderStroke(1.dp, ElectricCyan.copy(alpha = 0.25f)),
        modifier = Modifier.padding(horizontal = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = ElectricCyan,
                modifier = Modifier.size(12.dp)
            )
            Spacer(Modifier.width(5.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
