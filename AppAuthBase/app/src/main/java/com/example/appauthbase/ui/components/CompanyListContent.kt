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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appauthbase.data.remote.dto.CompanyDto
import com.example.appauthbase.presentation.CompanyListUiState
import com.example.appauthbase.theme.DarkGlass
import com.example.appauthbase.theme.DarkGlassElevated
import com.example.appauthbase.theme.DarkGlassHighlight
import com.example.appauthbase.theme.PureWhite
import com.example.appauthbase.theme.SpecularBorderSubtle
import com.example.appauthbase.theme.SpecularBorderWhite
import com.example.appauthbase.theme.TextJetBlack
import com.example.appauthbase.theme.TextPureWhite
import com.example.appauthbase.theme.TextSilver
import com.example.appauthbase.theme.WhiteToSilverGradient
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

    AnimatedAuroraBackground(modifier = modifier) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Diretório de Contas",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Black,
                                color = PureWhite
                            )
                            Spacer(Modifier.width(10.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = PureWhite,
                                border = androidx.compose.foundation.BorderStroke(1.dp, Color.White)
                            ) {
                                Text(
                                    text = "${uiState.companies.size} ENTIDADES",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Black,
                                    color = TextJetBlack,
                                    fontSize = 10.sp,
                                    letterSpacing = 1.sp,
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.5.dp)
                                )
                            }
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Voltar",
                                tint = PureWhite
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = PureWhite
                    )
                )
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = onAddClick,
                    containerColor = PureWhite,
                    contentColor = TextJetBlack,
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier
                        .shadow(
                            elevation = 16.dp,
                            shape = RoundedCornerShape(18.dp),
                            ambientColor = Color.White.copy(alpha = 0.5f),
                            spotColor = Color.White.copy(alpha = 0.5f)
                        )
                        .border(1.dp, Color.White, RoundedCornerShape(18.dp)),
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Nova Empresa",
                            tint = TextJetBlack
                        )
                    },
                    text = {
                        Text(
                            text = "Nova Empresa",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 0.5.sp,
                            color = TextJetBlack
                        )
                    }
                )
            },
            snackbarHost = {
                SnackbarHost(snackbarHostState) { data ->
                    Snackbar(
                        snackbarData = data,
                        shape = RoundedCornerShape(12.dp),
                        containerColor = DarkGlassElevated,
                        contentColor = PureWhite
                    )
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
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
                            label = "Buscar entidade",
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
                                            fontWeight = if (isSelected) FontWeight.Black else FontWeight.Medium,
                                            color = if (isSelected) TextJetBlack else PureWhite
                                        )
                                    },
                                    shape = RoundedCornerShape(10.dp),
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = PureWhite,
                                        containerColor = DarkGlassElevated.copy(alpha = 0.8f)
                                    ),
                                    border = FilterChipDefaults.filterChipBorder(
                                        enabled = true,
                                        selected = isSelected,
                                        borderColor = if (isSelected) PureWhite else DarkGlassHighlight
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
                                    color = PureWhite,
                                    strokeWidth = 3.dp
                                )
                                Spacer(Modifier.height(16.dp))
                                Text(
                                    text = "Sincronizando entidades corporativas...",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = TextSilver
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
                                verticalArrangement = Arrangement.spacedBy(12.dp)
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
                .clip(CircleShape)
                .background(PureWhite)
                .border(1.dp, Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Business,
                contentDescription = null,
                tint = TextJetBlack,
                modifier = Modifier.size(36.dp)
            )
        }

        Spacer(Modifier.height(22.dp))

        Text(
            text = "Base Corporativa Vazia",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            color = PureWhite
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Inicie o cadastro de contas, clientes e parceiros para alimentar o pipeline de vendas.",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSilver,
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
                .clip(CircleShape)
                .background(DarkGlassElevated),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.SearchOff,
                contentDescription = null,
                tint = PureWhite,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(Modifier.height(18.dp))

        Text(
            text = "Nenhuma entidade encontrada",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Black,
            color = PureWhite
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "Nenhum resultado corresponde aos critérios de pesquisa selecionados.",
            style = MaterialTheme.typography.bodySmall,
            color = TextSilver,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(18.dp))

        AndreaOutlinedButton(
            text = "Limpar Filtros",
            onClick = onClearFilters,
            modifier = Modifier.width(180.dp),
            height = 42.dp
        )
    }
}
