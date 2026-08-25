package com.example.appauthbase.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appauthbase.data.remote.dto.CompanyDto
import com.example.appauthbase.presentation.CompanyListUiState
import com.example.appauthbase.theme.NeoBackground
import com.example.appauthbase.theme.NeoBlack
import com.example.appauthbase.theme.NeoCardWhite
import com.example.appauthbase.theme.NeoLime
import com.example.appauthbase.theme.NeoTextDark
import com.example.appauthbase.theme.NeoTextMuted
import com.example.appauthbase.theme.NeoYellow
import com.example.appauthbase.theme.AppAuthBaseTheme

private val FAKE_COMPANIES = listOf(
    CompanyDto(companyId = 1, companyName = "Acme Inovações Corporativas", businessArea = "Tecnologia", companyEmail = "contato@acme.com", companyPhoneNumber = "(11) 9988-7766"),
    CompanyDto(companyId = 2, companyName = "Beta Capital Asset Management", businessArea = "Financeiro", companyEmail = "invest@betacapital.com", companyPhoneNumber = "(11) 3322-1100"),
    CompanyDto(companyId = 3, companyName = "Gamma Health Solutions", businessArea = "Saúde", companyEmail = "atendimento@gammasaude.com.br"),
    CompanyDto(companyId = 4, companyName = "Delta Strategic Advisory", businessArea = "Consultoria", companyEmail = "projetos@deltaconsultoria.com")
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
        containerColor = NeoBackground,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(end = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        NeoIconButton(
                            icon = Icons.AutoMirrored.Filled.ArrowBack,
                            onClick = onBack,
                            size = 42.dp
                        )

                        Text(
                            text = "Diretório de Contas",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Black,
                            color = NeoBlack
                        )

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = NeoYellow,
                            border = androidx.compose.foundation.BorderStroke(2.dp, NeoBlack)
                        ) {
                            Text(
                                text = "${uiState.companies.size} CONTAS",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Black,
                                color = NeoBlack,
                                fontSize = 10.sp,
                                modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NeoBackground,
                    titleContentColor = NeoBlack
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onAddClick,
                containerColor = NeoYellow,
                contentColor = NeoBlack,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .neoShadow(offsetX = 4.dp, offsetY = 4.dp, cornerRadius = 16.dp)
                    .border(2.5.dp, NeoBlack, RoundedCornerShape(16.dp)),
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Nova Empresa",
                        tint = NeoBlack
                    )
                },
                text = {
                    Text(
                        text = "Nova Empresa",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Black,
                        color = NeoBlack
                    )
                }
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .neoShadow(offsetX = 4.dp, offsetY = 4.dp, cornerRadius = 14.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(NeoCardWhite)
                        .border(2.5.dp, NeoBlack, RoundedCornerShape(14.dp))
                        .padding(14.dp)
                ) {
                    Text(
                        text = data.visuals.message,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Black,
                        color = NeoBlack
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(NeoBackground)
                .padding(padding)
        ) {
            // Search Bar & Filter Header
            if (uiState.companies.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    AndreaTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        label = "Buscar na base",
                        placeholder = "Filtrar por razão social, setor ou e-mail...",
                        leadingIcon = Icons.Default.Search
                    )

                    Spacer(Modifier.height(10.dp))

                    // Category Filter Chips
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val categories = listOf("Todas", "Tecnologia", "Financeiro", "Saúde", "Consultoria", "Comércio")
                        categories.forEach { category ->
                            val isSelected = selectedCategory == category
                            FilterChip(
                                selected = isSelected,
                                onClick = { selectedCategory = category },
                                label = {
                                    Text(
                                        text = category,
                                        fontWeight = if (isSelected) FontWeight.Black else FontWeight.Bold,
                                        color = NeoBlack
                                    )
                                },
                                shape = RoundedCornerShape(10.dp),
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
                                color = NeoBlack,
                                strokeWidth = 3.5.dp
                            )
                            Spacer(Modifier.height(16.dp))
                            Text(
                                text = "Carregando entidades corporativas...",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = NeoTextMuted
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
                                bottom = 96.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            items(filteredCompanies, key = { it.companyId ?: -1 }) { company ->
                                CompanyCorporateCard(
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
                .size(76.dp)
                .neoShadow(offsetX = 4.dp, offsetY = 4.dp, cornerRadius = 20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(NeoYellow)
                .border(2.5.dp, NeoBlack, RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Business,
                contentDescription = null,
                tint = NeoBlack,
                modifier = Modifier.size(36.dp)
            )
        }

        Spacer(Modifier.height(22.dp))

        Text(
            text = "Base Corporativa Vazia",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            color = NeoBlack
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Inicie o cadastro de contas, clientes e parceiros para alimentar o pipeline de vendas.",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = NeoTextMuted,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(26.dp))

        AndreaPrimaryButton(
            text = "Cadastrar Primeira Empresa",
            icon = Icons.Default.Add,
            onClick = onAddClick,
            modifier = Modifier.width(280.dp)
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
                .size(68.dp)
                .neoShadow(offsetX = 3.dp, offsetY = 3.dp, cornerRadius = 18.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(NeoCardWhite)
                .border(2.5.dp, NeoBlack, RoundedCornerShape(18.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.SearchOff,
                contentDescription = null,
                tint = NeoBlack,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(Modifier.height(18.dp))

        Text(
            text = "Nenhuma entidade encontrada",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Black,
            color = NeoBlack
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "Nenhum resultado corresponde aos critérios de pesquisa selecionados.",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = NeoTextMuted,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(18.dp))

        AndreaOutlinedButton(
            text = "Limpar Filtros",
            onClick = onClearFilters,
            modifier = Modifier.width(180.dp),
            height = 44.dp
        )
    }
}
