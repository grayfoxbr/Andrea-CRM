package com.example.appauthbase.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.appauthbase.presentation.CompanyFormUiState

/**
 * The editable fields of the company form organized in clear visual sections.
 */
@Composable
fun CompanyFormFields(
    uiState: CompanyFormUiState,
    onCompanyNameChange: (String) -> Unit,
    onCnpjChange: (String) -> Unit,
    onBusinessAreaChange: (String) -> Unit,
    onCompanyEmailChange: (String) -> Unit,
    onCompanyPhoneNumberChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val enabled = !uiState.isSaving

    Column(modifier = modifier.fillMaxWidth()) {
        // Section 1: Informações Principais
        FormSectionHeader(icon = Icons.Default.Business, title = "Identificação da Empresa")

        Spacer(Modifier.height(10.dp))

        AndreaTextField(
            value = uiState.companyName,
            onValueChange = onCompanyNameChange,
            label = "Nome da empresa *",
            placeholder = "Ex: Acme Inovações Ltda",
            leadingIcon = Icons.Default.Business,
            enabled = enabled,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        Spacer(Modifier.height(14.dp))

        AndreaTextField(
            value = uiState.cnpj,
            onValueChange = onCnpjChange,
            label = "CNPJ",
            placeholder = "00.000.000/0000-00",
            leadingIcon = Icons.Default.Badge,
            enabled = enabled,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
        )

        Spacer(Modifier.height(24.dp))

        // Section 2: Segmento & Área de Atuação
        FormSectionHeader(icon = Icons.Default.Work, title = "Segmento de Atuação")

        Spacer(Modifier.height(10.dp))

        AndreaTextField(
            value = uiState.businessArea,
            onValueChange = onBusinessAreaChange,
            label = "Área de atuação",
            placeholder = "Ex: Tecnologia, Financeiro, Consultoria...",
            leadingIcon = Icons.Default.Work,
            enabled = enabled,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        Spacer(Modifier.height(8.dp))

        // Quick suggestions chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val suggestions = listOf("Tecnologia", "Financeiro", "Consultoria", "Saúde", "Comércio", "Indústria", "Educação")
            suggestions.forEach { suggestion ->
                FilterChip(
                    selected = uiState.businessArea.equals(suggestion, ignoreCase = true),
                    onClick = { if (enabled) onBusinessAreaChange(suggestion) },
                    label = { Text(suggestion) },
                    shape = RoundedCornerShape(8.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedLabelColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // Section 3: Canais de Contato
        FormSectionHeader(icon = Icons.Default.Email, title = "Canais de Contato")

        Spacer(Modifier.height(10.dp))

        AndreaTextField(
            value = uiState.companyEmail,
            onValueChange = onCompanyEmailChange,
            label = "E-mail corporativo",
            placeholder = "contato@empresa.com",
            leadingIcon = Icons.Default.Email,
            enabled = enabled,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
        )

        Spacer(Modifier.height(14.dp))

        AndreaTextField(
            value = uiState.companyPhoneNumber,
            onValueChange = onCompanyPhoneNumberChange,
            label = "Telefone corporativo",
            placeholder = "(11) 99999-9999",
            leadingIcon = Icons.Default.Phone,
            enabled = enabled,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next)
        )

        Spacer(Modifier.height(24.dp))

        // Section 4: Detalhes & Descrição
        FormSectionHeader(icon = Icons.Default.Description, title = "Observações & Detalhes")

        Spacer(Modifier.height(10.dp))

        AndreaTextField(
            value = uiState.description,
            onValueChange = onDescriptionChange,
            label = "Descrição da empresa",
            placeholder = "Adicione informações relevantes sobre o cliente ou parceiro...",
            leadingIcon = Icons.Default.Description,
            singleLine = false,
            minLines = 3,
            enabled = enabled,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
    }
}

@Composable
private fun FormSectionHeader(icon: ImageVector, title: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(18.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
