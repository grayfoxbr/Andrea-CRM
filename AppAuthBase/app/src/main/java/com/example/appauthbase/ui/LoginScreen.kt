package com.example.appauthbase.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.appauthbase.presentation.AuthViewModel
import com.example.appauthbase.ui.components.LoginContent

/**
 * Stateful entry point: owns the connection to [AuthViewModel] and
 * delegates all rendering to the stateless [LoginContent].
 */
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val uiState by authViewModel.uiState.collectAsState()

    LoginContent(
        uiState = uiState,
        onLoginClick = onLoginClick,
        onRegisterClick = onRegisterClick,
        onLogoutClick = authViewModel::logout
    )
}
