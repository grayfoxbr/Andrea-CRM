package com.example.appauthbase.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appauthbase.data.remote.dto.CompanyDto
import com.example.appauthbase.presentation.CompanyListUiState
import com.example.appauthbase.theme.AndreaPrimary
import com.example.appauthbase.theme.AndreaPrimaryGradient
import com.example.appauthbase.theme.AppAuthBaseTheme

private val FAKE_COMPANIES = listOf(
    CompanyDto(companyId = 1, companyName = "Acme Inovações Ltda", businessArea = "Tecnologia", companyEmail = "contato@acme.com", companyPhoneNumber = "(11) 9988-7766"),
    CompanyDto(companyId = 2, companyName = "Beta Capital Investimentos", businessArea = "Financeiro", companyEmail = "invest@betacapital.com", companyPhoneNumber = "(11) 3322-1100"),
    CompanyDto(companyId = 3, companyName = "Gamma Soluções em Saúde", businessArea = "Saúde", companyEmail = "atendimento@gammasaude.com.br"),
    CompanyDto(companyId = 4, companyName = "Delta Consultoria Empresarial", businessArea = "Consultoria", companyEmail = "projetos@deltaconsultoria.com")
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
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var companyPendingDelete by rememberSaveable { mutableStateOf<Long?>(null) }
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var selectedCategory by rememberSaveable { mutableStateOf("Todas") }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            onErrorConsumed()
        }
    }

    // Filter companies based on search and category
    val filteredCompanies = remember(uiState.companies, searchQuery, selectedCategory) {
        uiState.companies.filter { company ->
            val matchesQuery = searchQuery.isBlank() ||
                    company.companyName.contains(searchQuery, ignoreCase = true) ||
                    (company.businessArea?.contains(searchQuery, ignoreCase = true) == true) ||
                    (company.companyEmail?.contains(searchQuery, ignoreCase = true) == true)

            val matchesCategory = selectedCategory == "Todas" ||
                    (company.businessArea?.equals(selectedCategory, ignoreCase = true) == true)

            matchesQuery && matchesCategory
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Empresas",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.width(8.dp))
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
                        ) {
                            Text(
                                text = "${uiState.companies.size}",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }
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
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onAddClick,
                containerColor = Color.Transparent,
                contentColor = Color.White,
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(18.dp),
                        ambientColor = AndreaPrimary.copy(alpha = 0.5f),
                        spotColor = AndreaPrimary.copy(alpha = 0.5f)
                    )
                    .background(AndreaPrimaryGradient, shape = RoundedCornerShape(18.dp)),
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Nova Empresa",
                        tint = Color.White
                    )
                },
                text = {
                    Text(
                        text = "Nova Empresa",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
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
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
        ) {
            // Search Bar & Filter Header
            if (uiState.companies.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    AndreaTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        label = "Buscar empresas",
                        placeholder = "Filtrar por nome, área ou e-mail...",
                        leadingIcon = Icons.Default.Search
                    )

                    Spacer(Modifier.height(8.dp))

                    // Quick Category Filter Chips
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val categories = listOf("Todas", "Tecnologia", "Financeiro", "Saúde", "Consultoria", "Comércio")
                        categories.forEach { category ->
                            FilterChip(
                                selected = selectedCategory == category,
                                onClick = { selectedCategory = category },
                                label = { Text(category) },
                                shape = RoundedCornerShape(10.dp),
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                    selectedLabelColor = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                when {
                    uiState.isLoading -> {
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
                                text = "Carregando base de empresas...",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    uiState.companies.isEmpty() -> {
                        EmptyCompaniesMessage(onAddClick = onAddClick)
                    }

                    filteredCompanies.isEmpty() -> {
                        EmptySearchMessage(searchQuery = searchQuery) {
                            searchQuery = ""
                            selectedCategory = "Todas"
                        }
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(
                                start = 16.dp,
                                end = 16.dp,
                                top = 8.dp,
                                bottom = 88.dp // padding for Extended FAB
                            ),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(filteredCompanies, key = { it.companyId ?: -1 }) { company ->
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
private fun EmptyCompaniesMessage(onAddClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Business,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(36.dp)
            )
        }

        Spacer(Modifier.height(20.dp))

        Text(
            text = "Nenhuma empresa cadastrada",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Comece construindo sua base de clientes e parceiros corporativos.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(24.dp))

        AndreaPrimaryButton(
            text = "Cadastrar Primeira Empresa",
            icon = Icons.Default.Add,
            onClick = onAddClick,
            modifier = Modifier.width(260.dp)
        )
    }
}

@Composable
private fun EmptySearchMessage(
    searchQuery: String,
    onClearFilters: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.SearchOff,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Nenhum resultado encontrado",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "Não encontramos nenhuma empresa correspondente ao filtro.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(16.dp))

        AndreaOutlinedButton(
            text = "Limpar Filtros",
            onClick = onClearFilters,
            modifier = Modifier.width(200.dp),
            height = 42.dp
        )
    }
}
