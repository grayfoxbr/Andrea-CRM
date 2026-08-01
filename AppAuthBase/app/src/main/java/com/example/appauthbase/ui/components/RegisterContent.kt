package com.example.appauthbase.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appauthbase.presentation.RegisterUiState

/**
 * Pure UI for the registration screen. Knows nothing about
 * [com.example.appauthbase.presentation.RegisterViewModel].
 */

import androidx.compose.ui.tooling.preview.Preview
import com.example.appauthbase.theme.AppAuthBaseTheme

@Preview(showBackground = true, name = "Default")
@Composable
private fun RegisterContentPreview() {
    AppAuthBaseTheme {
        RegisterContent(
            uiState = RegisterUiState(),
            email = "usuario@email.com",
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
            email = "usuario@email.com",
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
            email = "usuario@email.com",
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
            email = "usuario@email.com",
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
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Create Account", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))

        RegisterForm(
            email = email,
            password = password,
            confirmPassword = confirmPassword,
            enabled = !uiState.loading,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onConfirmPasswordChange = onConfirmPasswordChange
        )

        Spacer(Modifier.height(24.dp))

        if (uiState.loading) {
            CircularProgressIndicator()
        } else {
            Button(onClick = onRegisterClick) {
                Text("Register")
            }
        }

        uiState.error?.let {
            Spacer(Modifier.height(16.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        if (uiState.success) {
            Spacer(Modifier.height(16.dp))
            Text("Account created successfully")
        }

        Spacer(Modifier.height(16.dp))

        TextButton(onClick = onBack) {
            Text("Back to login")
        }
    }
}
