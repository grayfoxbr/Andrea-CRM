package com.example.appauthbase.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

/**
 * Email / password / confirm-password fields shared by the register flow.
 * Purely presentational — reports changes through callbacks only.
 */
@Composable
fun RegisterForm(
    email: String,
    password: String,
    confirmPassword: String,
    enabled: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit
) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        enabled = enabled,
        label = { Text("Email") }
    )

    Spacer(Modifier.height(12.dp))

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        enabled = enabled,
        label = { Text("Password") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )

    Spacer(Modifier.height(12.dp))

    OutlinedTextField(
        value = confirmPassword,
        onValueChange = onConfirmPasswordChange,
        enabled = enabled,
        label = { Text("Confirm password") },
        visualTransformation = PasswordVisualTransformation()
    )
}
