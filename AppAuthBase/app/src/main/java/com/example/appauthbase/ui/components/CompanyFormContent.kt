package com.example.appauthbase.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appauthbase.presentation.CompanyFormUiState
import com.example.appauthbase.theme.ElectricCyan
import com.example.appauthbase.theme.ObsidianSurface
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
                companyName = "Acme Inovações Corporativas",
                cnpj = "12.345.678/0001-99",
                businessArea = "Tecnologia",
                companyEmail = "contato@acme.com.br",
                companyPhoneNumber = "(11) 99999-0000",
                description = "Empresa líder em inovação tecnológica"
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
    val isDark = isSystemInDarkTheme()

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
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    shape = RoundedCornerShape(12.dp),
                    containerColor = if (isDark) ObsidianSurface else MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurface
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
                        color = ElectricCyan,
                        strokeWidth = 3.dp
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Carregando dados da entidade...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 18.dp, vertical = 16.dp)
                ) {
                    // Live Hologram Corporate Pass Preview
                    CorporatePassHologram(
                        companyName = uiState.companyName,
                        cnpj = uiState.cnpj,
                        businessArea = uiState.businessArea,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )

                    // Form Fields
                    CompanyFormFields(
                        uiState = uiState,
                        onCompanyNameChange = onCompanyNameChange,
                        onCnpjChange = onCnpjChange,
                        onBusinessAreaChange = onBusinessAreaChange,
                        onCompanyEmailChange = onCompanyEmailChange,
                        onCompanyPhoneNumberChange = onCompanyPhoneNumberChange,
                        onDescriptionChange = onDescriptionChange
                    )

                    Spacer(Modifier.height(24.dp))

                    AndreaPrimaryButton(
                        text = if (isEditMode) "Salvar Alterações" else "Cadastrar Empresa",
                        icon = if (isEditMode) Icons.Default.Save else Icons.Default.Check,
                        isLoading = uiState.isSaving,
                        enabled = uiState.companyName.isNotBlank(),
                        onClick = onSaveClick
                    )

                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }
}
