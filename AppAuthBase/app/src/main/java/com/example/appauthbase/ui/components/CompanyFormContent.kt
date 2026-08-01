package com.example.appauthbase.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import com.example.appauthbase.presentation.CompanyFormUiState

/**
 * Pure UI for the createee/edit company screen. Receives state and
 * reports intent through callbacks — has no knowledge of
 * [com.example.appauthbase.presentation.CompanyFormViewModel].
 */

import androidx.compose.ui.tooling.preview.Preview
import com.example.appauthbase.theme.AppAuthBaseTheme

@Preview(showBackground = true, name = "Criação")
@Composable
private fun CompanyFormContentCreatePreview() {
    AppAuthBaseTheme {
        CompanyFormContent(
            uiState = CompanyFormUiState(companyName = "Nova Empresa"),
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
                description = "Empresa de tecnologia"
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

@Preview(showBackground = true, name = "Carregando inicial")
@Composable
private fun CompanyFormContentLoadingPreview() {
    AppAuthBaseTheme {
        CompanyFormContent(
            uiState = CompanyFormUiState(isLoadingInitial = true),
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
    onBack: () -> Unit
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
                title = { Text(if (isEditMode) "Editar Empresa" else "Nova Empresa") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data -> Snackbar(snackbarData = data) }
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (uiState.isLoadingInitial) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
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

                    Spacer(Modifier.height(24.dp))

                    if (uiState.isSaving) {
                        CircularProgressIndicator()
                    } else {
                        Button(
                            onClick = onSaveClick,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(if (isEditMode) "Salvar alterações" else "Criar empresa")
                        }
                    }
                }
            }
        }
    }
}
