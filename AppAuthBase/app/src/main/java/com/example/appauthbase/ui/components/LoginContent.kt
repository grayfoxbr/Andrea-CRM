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
import com.example.appauthbase.presentation.AuthStateData

/**
 * Pure UI for the login screen. Knows nothing about [com.example.appauthbase.presentation.AuthViewModel] —
 * it only receives state and reports user intent through callbacks.
 */

import androidx.compose.ui.tooling.preview.Preview
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
            uiState = AuthStateData(errorMessage = "Falha na autenticação"),
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
            uiState = AuthStateData(isLoggedIn = true, accessToken = "abc123"),
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
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            uiState.isLoading -> LoginLoadingSection()
            uiState.isLoggedIn -> LoggedInSection(onLogoutClick = onLogoutClick)
            else -> LoggedOutSection(
                errorMessage = uiState.errorMessage,
                onLoginClick = onLoginClick,
                onRegisterClick = onRegisterClick
            )
        }
    }
}

@Composable
private fun LoginLoadingSection() {
    CircularProgressIndicator()
    Spacer(Modifier.height(16.dp))
    Text("Authenticating...")
}

@Composable
private fun LoggedInSection(onLogoutClick: () -> Unit) {
    Text(
        "Welcome! You are authenticated.",
        style = MaterialTheme.typography.headlineSmall
    )
    Spacer(Modifier.height(16.dp))
    Button(onClick = onLogoutClick) {
        Text("Logout")
    }
}

@Composable
private fun LoggedOutSection(
    errorMessage: String?,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Text("AppAuth Demo", style = MaterialTheme.typography.headlineMedium)
    Spacer(Modifier.height(24.dp))

    Button(onClick = onLoginClick) {
        Text("Login")
    }

    Spacer(Modifier.height(8.dp))

    TextButton(onClick = onRegisterClick) {
        Text("Create account")
    }

    errorMessage?.let { message ->
        Spacer(Modifier.height(16.dp))
        Text(text = message, color = MaterialTheme.colorScheme.error)
    }


}


