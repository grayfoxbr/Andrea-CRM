package com.example.appauthbase.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appauthbase.presentation.RegisterUiState
import com.example.appauthbase.theme.PureWhite
import com.example.appauthbase.theme.TextSilver
import com.example.appauthbase.theme.AppAuthBaseTheme

@Preview(showBackground = true, name = "Default")
@Composable
private fun RegisterContentPreview() {
    AppAuthBaseTheme {
        RegisterContent(
            uiState = RegisterUiState(),
            email = "usuario@empresa.com",
            password = "123456",
            confirmPassword = "123456",
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onRegisterClick = {},
            onBack = {}
        )
    }
}

@Preview(showBackground = true, name = "Loading")
@Composable
private fun RegisterContentLoadingPreview() {
    AppAuthBaseTheme {
        RegisterContent(
            uiState = RegisterUiState(loading = true),
            email = "usuario@empresa.com",
            password = "123456",
            confirmPassword = "123456",
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onRegisterClick = {},
            onBack = {}
        )
    }
}

@Preview(showBackground = true, name = "Error")
@Composable
private fun RegisterContentErrorPreview() {
    AppAuthBaseTheme {
        RegisterContent(
            uiState = RegisterUiState(error = "As senhas não coincidem"),
            email = "usuario@empresa.com",
            password = "123456",
            confirmPassword = "1234567",
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onRegisterClick = {},
            onBack = {}
        )
    }
}

@Preview(showBackground = true, name = "Success")
@Composable
private fun RegisterContentSuccessPreview() {
    AppAuthBaseTheme {
        RegisterContent(
            uiState = RegisterUiState(success = true),
            email = "usuario@empresa.com",
            password = "123456",
            confirmPassword = "123456",
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onRegisterClick = {},
            onBack = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent(
    uiState: RegisterUiState,
    email: String,
    password: String,
    confirmPassword: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedAuroraBackground(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar ao login",
                        tint = PureWhite
                    )
                }
            }

            Spacer(Modifier.height(4.dp))

            BespokeLogo(isLarge = false)

            Spacer(Modifier.height(28.dp))

            LuxuryGlassCard(cornerRadius = 24.dp) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(26.dp)
                ) {
                    Text(
                        text = "Criar Nova Conta",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Black,
                        color = PureWhite
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = "Informe seus dados de acesso corporativo",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSilver
                    )

                    Spacer(Modifier.height(22.dp))

                    RegisterForm(
                        email = email,
                        password = password,
                        confirmPassword = confirmPassword,
                        enabled = !uiState.loading,
                        onEmailChange = onEmailChange,
                        onPasswordChange = onPasswordChange,
                        onConfirmPasswordChange = onConfirmPasswordChange
                    )

                    if (uiState.error != null) {
                        Spacer(Modifier.height(16.dp))
                        AndreaStatusBanner(
                            message = uiState.error,
                            type = BannerType.ERROR
                        )
                    }

                    if (uiState.success) {
                        Spacer(Modifier.height(16.dp))
                        AndreaStatusBanner(
                            message = "Conta criada com sucesso! Faça login para continuar.",
                            type = BannerType.SUCCESS
                        )
                    }

                    Spacer(Modifier.height(24.dp))

                    AndreaPrimaryButton(
                        text = "Concluir Cadastro",
                        icon = Icons.Default.PersonAdd,
                        isLoading = uiState.loading,
                        onClick = onRegisterClick
                    )

                    Spacer(Modifier.height(14.dp))

                    TextButton(
                        onClick = onBack,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Já possui uma conta? Entrar",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = PureWhite
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}
