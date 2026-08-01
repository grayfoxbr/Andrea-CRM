package com.example.appauthbase.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.appauthbase.presentation.CompanyFormViewModel
import com.example.appauthbase.ui.components.CompanyFormContent

/**
 * Stateful entry point: owns the connection to [CompanyFormViewModel]
 * and delegates all rendering to the stateless [CompanyFormContent].
 */
@Composable
fun CompanyFormScreen(
    viewModel: CompanyFormViewModel,
    onSaveSuccess: () -> Unit,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    CompanyFormContent(
        uiState = uiState,
        isEditMode = viewModel.isEditMode,
        onCompanyNameChange = viewModel::onCompanyNameChange,
        onCnpjChange = viewModel::onCnpjChange,
        onBusinessAreaChange = viewModel::onBusinessAreaChange,
        onCompanyEmailChange = viewModel::onCompanyEmailChange,
        onCompanyPhoneNumberChange = viewModel::onCompanyPhoneNumberChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onSaveClick = viewModel::save,
        onErrorConsumed = viewModel::consumeError,
        onSaveSuccess = onSaveSuccess,
        onBack = onBack
    )
}
