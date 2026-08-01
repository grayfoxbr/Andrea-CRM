package com.example.appauthbase.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appauthbase.presentation.CompanyListUiState

/**
 * Pure UI for the company list screen. Has no knowledge of
 * [com.example.appauthbase.presentation.CompanyListViewModel].
 */

import androidx.compose.ui.tooling.preview.Preview
import com.example.appauthbase.data.remote.dto.CompanyDto
import com.example.appauthbase.theme.AppAuthBaseTheme

private val FAKE_COMPANIES = listOf(
    CompanyDto(companyId = 1, companyName = "Acme Ltda", businessArea = "Tecnologia", companyEmail = "contato@acme.com"),
    CompanyDto(companyId = 2, companyName = "Beta Corp", businessArea = "Financeiro"),
    CompanyDto(companyId = 3, companyName = "Gamma S.A.")
)

@Preview(showBackground = true, name = "Com dados")
@Composable
private fun CompanyListContentPreview() {
    AppAuthBaseTheme {
        CompanyListContent(
            uiState = CompanyListUiState(companies = FAKE_COMPANIES),
            onAddClick = {},
            onCompanyClick = {},
            onDeleteConfirmed = {},
            onErrorConsumed = {},
            onBack = {}
        )
    }
}

@Preview(showBackground = true, name = "Carregando")
@Composable
private fun CompanyListContentLoadingPreview() {
    AppAuthBaseTheme {
        CompanyListContent(
            uiState = CompanyListUiState(isLoading = true),
            onAddClick = {},
            onCompanyClick = {},
            onDeleteConfirmed = {},
            onErrorConsumed = {},
            onBack = {}
        )
    }
}

@Preview(showBackground = true, name = "Vazio")
@Composable
private fun CompanyListContentEmptyPreview() {
    AppAuthBaseTheme {
        CompanyListContent(
            uiState = CompanyListUiState(companies = emptyList()),
            onAddClick = {},
            onCompanyClick = {},
            onDeleteConfirmed = {},
            onErrorConsumed = {},
            onBack = {}
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyListContent(
    uiState: CompanyListUiState,
    onAddClick: () -> Unit,
    onCompanyClick: (Long) -> Unit,
    onDeleteConfirmed: (Long) -> Unit,
    onErrorConsumed: () -> Unit,
    onBack: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var companyPendingDelete by rememberSaveable { mutableStateOf<Long?>(null) }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            onErrorConsumed()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Empresas") },
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
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Filled.Add, contentDescription = "Nova empresa")
            }
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
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                uiState.companies.isEmpty() -> {
                    EmptyCompaniesMessage()
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(uiState.companies, key = { it.companyId ?: -1 }) { company ->
                            CompanyCard(
                                company = company,
                                onClick = { company.companyId?.let(onCompanyClick) },
                                onDeleteClick = { companyPendingDelete = company.companyId }
                            )
                        }
                    }
                }
            }
        }
    }

    companyPendingDelete?.let { id ->
        DeleteCompanyDialog(
            onConfirm = {
                onDeleteConfirmed(id)
                companyPendingDelete = null
            },
            onDismiss = { companyPendingDelete = null }
        )
    }
}

@Composable
private fun EmptyCompaniesMessage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Nenhuma empresa cadastrada ainda.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
