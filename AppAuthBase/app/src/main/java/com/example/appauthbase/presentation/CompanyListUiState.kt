package com.example.appauthbase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appauthbase.data.remote.dto.CompanyDto
import com.example.appauthbase.domain.usecase.company.DeleteCompanyUseCase
import com.example.appauthbase.domain.usecase.company.GetCompaniesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CompanyListUiState(
    val companies: List<CompanyDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class CompanyListViewModel(
    private val getCompanies: GetCompaniesUseCase,
    private val deleteCompany: DeleteCompanyUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CompanyListUiState())
    val uiState: StateFlow<CompanyListUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = getCompanies()

            result.fold(
                onSuccess = { companies ->
                    _uiState.update { it.copy(companies = companies, isLoading = false) }
                },
                onFailure = { e ->
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = e.message ?: "Erro ao carregar empresas")
                    }
                }
            )
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch {
            val result = deleteCompany(id)

            result.fold(
                onSuccess = { load() },
                onFailure = { e ->
                    _uiState.update {
                        it.copy(errorMessage = e.message ?: "Erro ao excluir empresa")
                    }
                }
            )
        }
    }

    fun consumeError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}