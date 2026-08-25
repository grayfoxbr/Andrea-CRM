package com.example.appauthbase.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

/**
 * Email / password / confirm-password fields shared by the register flow.
 * Styled using Andrea CRM custom text fields.
 */
@Composable
fun RegisterForm(
    email: String,
    password: String,
    confirmPassword: String,
    enabled: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        AndreaTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "E-mail corporativo",
            placeholder = "usuario@empresa.com",
            leadingIcon = Icons.Default.Email,
            enabled = enabled,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        Spacer(Modifier.height(16.dp))

        AndreaTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = "Senha de acesso",
            placeholder = "Mínimo 6 caracteres",
            leadingIcon = Icons.Default.Lock,
            isPassword = true,
            enabled = enabled,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        )

        Spacer(Modifier.height(16.dp))

        AndreaTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = "Confirmar senha",
            placeholder = "Repita sua senha",
            leadingIcon = Icons.Default.VerifiedUser,
            isPassword = true,
            enabled = enabled,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
    }
}
