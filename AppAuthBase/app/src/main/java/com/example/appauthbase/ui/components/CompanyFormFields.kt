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
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appauthbase.presentation.CompanyFormUiState
import com.example.appauthbase.theme.NeoBlack
import com.example.appauthbase.theme.NeoCardWhite
import com.example.appauthbase.theme.NeoYellow

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
        // Section 1: Identificação Corporativa
        LuxuryGlassCard {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                FormSectionHeader(icon = Icons.Default.Business, title = "IDENTIFICAÇÃO CORPORATIVA")

                Spacer(Modifier.height(14.dp))

                AndreaTextField(
                    value = uiState.companyName,
                    onValueChange = onCompanyNameChange,
                    label = "Razão Social / Nome Fantasia *",
                    placeholder = "Ex: Acme Inovações Corporativas",
                    leadingIcon = Icons.Default.Business,
                    enabled = enabled,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                Spacer(Modifier.height(14.dp))

                AndreaTextField(
                    value = uiState.cnpj,
                    onValueChange = onCnpjChange,
                    label = "CNPJ (opcional)",
                    placeholder = "00.000.000/0000-00",
                    leadingIcon = Icons.Default.Badge,
                    enabled = enabled,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Section 2: Segmento de Mercado
        LuxuryGlassCard {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                FormSectionHeader(icon = Icons.Default.Work, title = "SEGMENTO & MERCADO")

                Spacer(Modifier.height(14.dp))

                AndreaTextField(
                    value = uiState.businessArea,
                    onValueChange = onBusinessAreaChange,
                    label = "Área de Atuação",
                    placeholder = "Ex: Tecnologia, Finanças, Saúde...",
                    leadingIcon = Icons.Default.Work,
                    enabled = enabled,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                Spacer(Modifier.height(10.dp))

                // Quick tags
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val suggestions = listOf("Tecnologia", "Financeiro", "Consultoria", "Saúde", "Comércio", "Indústria")
                    suggestions.forEach { suggestion ->
                        val isSelected = uiState.businessArea.equals(suggestion, ignoreCase = true)
                        FilterChip(
                            selected = isSelected,
                            onClick = { if (enabled) onBusinessAreaChange(suggestion) },
                            label = {
                                Text(
                                    text = suggestion,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Black else FontWeight.Bold,
                                    color = NeoBlack
                                )
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = NeoYellow,
                                containerColor = NeoCardWhite
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = isSelected,
                                borderColor = NeoBlack,
                                borderWidth = 2.dp
                            )
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Section 3: Contatos
        LuxuryGlassCard {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                FormSectionHeader(icon = Icons.Default.Email, title = "CANAIS DE CONTATO")

                Spacer(Modifier.height(14.dp))

                AndreaTextField(
                    value = uiState.companyEmail,
                    onValueChange = onCompanyEmailChange,
                    label = "E-mail Corporativo",
                    placeholder = "contato@empresa.com.br",
                    leadingIcon = Icons.Default.Email,
                    enabled = enabled,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
                )

                Spacer(Modifier.height(14.dp))

                AndreaTextField(
                    value = uiState.companyPhoneNumber,
                    onValueChange = onCompanyPhoneNumberChange,
                    label = "Telefone Corporativo",
                    placeholder = "(11) 99999-9999",
                    leadingIcon = Icons.Default.Phone,
                    enabled = enabled,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next)
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Section 4: Notas e Observações
        LuxuryGlassCard {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                FormSectionHeader(icon = Icons.Default.Description, title = "OBSERVAÇÕES E NOTAS")

                Spacer(Modifier.height(14.dp))

                AndreaTextField(
                    value = uiState.description,
                    onValueChange = onDescriptionChange,
                    label = "Descrição da Conta",
                    placeholder = "Notas estratégicas, histórico de prospecção ou informações adicionais...",
                    leadingIcon = Icons.Default.Description,
                    singleLine = false,
                    minLines = 3,
                    enabled = enabled,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )
            }
        }
    }
}

@Composable
private fun FormSectionHeader(icon: ImageVector, title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = NeoBlack,
            modifier = Modifier.size(18.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.sp,
            color = NeoBlack
        )
    }
}
