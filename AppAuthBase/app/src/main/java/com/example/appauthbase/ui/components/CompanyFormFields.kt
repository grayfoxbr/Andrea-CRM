package com.example.appauthbase.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.appauthbase.presentation.CompanyFormUiState

/**
 * The editable fields of the company form. Purely presentational —
 * all state changes are reported through the on*Change callbacks.
 */
@Composable
fun CompanyFormFields(
    uiState: CompanyFormUiState,
    onCompanyNameChange: (String) -> Unit,
    onCnpjChange: (String) -> Unit,
    onBusinessAreaChange: (String) -> Unit,
    onCompanyEmailChange: (String) -> Unit,
    onCompanyPhoneNumberChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit
) {
    val enabled = !uiState.isSaving

    OutlinedTextField(
        value = uiState.companyName,
        onValueChange = onCompanyNameChange,
        label = { Text("Nome da empresa *") },
        singleLine = true,
        enabled = enabled,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(Modifier.height(12.dp))

    OutlinedTextField(
        value = uiState.cnpj,
        onValueChange = onCnpjChange,
        label = { Text("CNPJ") },
        singleLine = true,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(Modifier.height(12.dp))

    OutlinedTextField(
        value = uiState.businessArea,
        onValueChange = onBusinessAreaChange,
        label = { Text("Área de atuação") },
        singleLine = true,
        enabled = enabled,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(Modifier.height(12.dp))

    OutlinedTextField(
        value = uiState.companyEmail,
        onValueChange = onCompanyEmailChange,
        label = { Text("E-mail") },
        singleLine = true,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(Modifier.height(12.dp))

    OutlinedTextField(
        value = uiState.companyPhoneNumber,
        onValueChange = onCompanyPhoneNumberChange,
        label = { Text("Telefone") },
        singleLine = true,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(Modifier.height(12.dp))

    OutlinedTextField(
        value = uiState.description,
        onValueChange = onDescriptionChange,
        label = { Text("Descrição") },
        enabled = enabled,
        minLines = 3,
        modifier = Modifier.fillMaxWidth()
    )
}
