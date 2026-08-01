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
 * Pure UI for the create/edit company screen. Receives state and
 * reports intent through callbacks — has no knowledge of
 * [com.example.appauthbase.presentation.CompanyFormViewModel].
 */
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
