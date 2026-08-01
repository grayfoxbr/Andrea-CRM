package com.example.appauthbase.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.appauthbase.presentation.RegisterViewModel
import com.example.appauthbase.ui.components.RegisterContent

/**
 * Stateful entry point for registration: owns the [RegisterViewModel]
 * connection and the local form-field state, and delegates all
 * rendering to the stateless [RegisterContent].
 */
@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel,
    onBack: () -> Unit
) {
    val uiState by registerViewModel.ui.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    RegisterContent(
        uiState = uiState,
        email = email,
        password = password,
        confirmPassword = confirmPassword,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onConfirmPasswordChange = { confirmPassword = it },
        onRegisterClick = {
            registerViewModel.register(
                email = email,
                password = password,
                confirm = confirmPassword
            )
        },
        onBack = onBack
    )
}
