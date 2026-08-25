package com.example.appauthbase.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appauthbase.data.remote.dto.CompanyDto
import com.example.appauthbase.presentation.CompanyFormUiState
import com.example.appauthbase.theme.AndreaPrimary
import com.example.appauthbase.theme.AndreaPrimaryGradient
import com.example.appauthbase.theme.AppAuthBaseTheme

@Preview(showBackground = true, name = "Criação")
@Composable
private fun CompanyFormContentCreatePreview() {
    AppAuthBaseTheme {
        CompanyFormContent(
            uiState = CompanyFormUiState(companyName = "Nova Empresa Tech"),
            isEditMode = false,
            onCompanyNameChange = {},
            onCnpjChange = {},
            onBusinessAreaChange = {},
            onCompanyEmailChange = {},
            onCompanyPhoneNumberChange = {},
            onDescriptionChange = {},
            onSaveClick = {},
            onErrorConsumed = {},
            onSaveSuccess = {},
            onBack = {}
        )
    }
}

@Preview(showBackground = true, name = "Edição")
@Composable
private fun CompanyFormContentEditPreview() {
    AppAuthBaseTheme {
        CompanyFormContent(
            uiState = CompanyFormUiState(
                companyName = "Acme Ltda",
                cnpj = "12.345.678/0001-99",
                businessArea = "Tecnologia",
                companyEmail = "contato@acme.com",
                companyPhoneNumber = "(11) 99999-0000",
                description = "Empresa de tecnologia e inovação"
            ),
            isEditMode = true,
            onCompanyNameChange = {},
            onCnpjChange = {},
            onBusinessAreaChange = {},
            onCompanyEmailChange = {},
            onCompanyPhoneNumberChange = {},
            onDescriptionChange = {},
            onSaveClick = {},
            onErrorConsumed = {},
            onSaveSuccess = {},
            onBack = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyFormContent(
    uiState: CompanyFormUiState,
    isEditMode: Boolean,
    onCompanyNameChange: (String) -> Unit,
    onCnpjChange: (String) -> Unit,
    onBusinessAreaChange: (String) -> Unit,
    onCompanyEmailChange: (String) -> Unit,
    onCompanyPhoneNumberChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onErrorConsumed: () -> Unit,
    onSaveSuccess: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            onErrorConsumed()
        }
    }

    LaunchedEffect(uiState.saveSuccess) {
        if (uiState.saveSuccess) onSaveSuccess()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isEditMode) "Editar Empresa" else "Nova Empresa",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    shape = RoundedCornerShape(12.dp),
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
        ) {
            if (uiState.isLoadingInitial) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 3.dp
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Carregando dados da empresa...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                ) {
                    // Live Preview Card (if company name is typed)
                    AnimatedVisibility(
                        visible = uiState.companyName.isNotBlank(),
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Column(modifier = Modifier.padding(bottom = 20.dp)) {
                            Text(
                                text = "Pré-visualização do Card",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(Modifier.height(8.dp))
                            CompanyCard(
                                company = CompanyDto(
                                    companyId = null,
                                    companyName = uiState.companyName,
                                    cnpj = uiState.cnpj,
                                    businessArea = uiState.businessArea,
                                    companyEmail = uiState.companyEmail,
                                    companyPhoneNumber = uiState.companyPhoneNumber,
                                    description = uiState.description
                                ),
                                onClick = {},
                                onDeleteClick = {}
                            )
                        }
                    }

                    // Form Container Card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        border = CardDefaults.outlinedCardBorder().copy(
                            brush = Brush.linearGradient(
                                listOf(
                                    MaterialTheme.colorScheme.outline.copy(alpha = 0.35f),
                                    MaterialTheme.colorScheme.outline.copy(alpha = 0.1f)
                                )
                            ),
                            width = 1.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        ) {
                            CompanyFormFields(
                                uiState = uiState,
                                onCompanyNameChange = onCompanyNameChange,
                                onCnpjChange = onCnpjChange,
                                onBusinessAreaChange = onBusinessAreaChange,
                                onCompanyEmailChange = onCompanyEmailChange,
                                onCompanyPhoneNumberChange = onCompanyPhoneNumberChange,
                                onDescriptionChange = onDescriptionChange
                            )

                            Spacer(Modifier.height(32.dp))

                            AndreaPrimaryButton(
                                text = if (isEditMode) "Salvar Alterações" else "Cadastrar Empresa",
                                icon = if (isEditMode) Icons.Default.Save else Icons.Default.Check,
                                isLoading = uiState.isSaving,
                                enabled = uiState.companyName.isNotBlank(),
                                onClick = onSaveClick
                            )
                        }
                    }

                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }
}
