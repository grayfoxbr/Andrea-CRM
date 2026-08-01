package com.example.appauthbase.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.appauthbase.presentation.CompanyListViewModel
import com.example.appauthbase.ui.components.CompanyListContent

/**
 * Stateful entry point: owns the connection to [CompanyListViewModel]
 * and delegates all rendering to the stateless [CompanyListContent].
 */
@Composable
fun CompanyListScreen(
    viewModel: CompanyListViewModel,
    onAddClick: () -> Unit,
    onCompanyClick: (Long) -> Unit,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    CompanyListContent(
        uiState = uiState,
        onAddClick = onAddClick,
        onCompanyClick = onCompanyClick,
        onDeleteConfirmed = viewModel::delete,
        onErrorConsumed = viewModel::consumeError,
        onBack = onBack
    )
}
