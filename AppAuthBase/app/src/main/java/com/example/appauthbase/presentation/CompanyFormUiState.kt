package com.example.appauthbase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appauthbase.data.remote.dto.CompanyRequestDto
import com.example.appauthbase.data.remote.dto.CompanyUpdateRequestDto
import com.example.appauthbase.domain.usecase.company.CreateCompanyUseCase
import com.example.appauthbase.domain.usecase.company.GetCompanyByIdUseCase
import com.example.appauthbase.domain.usecase.company.UpdateCompanyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CompanyFormUiState(
    val companyName: String = "",
    val cnpj: String = "",
    val description: String = "",
    val companyEmail: String = "",
    val companyPhoneNumber: String = "",
    val businessArea: String = "",
    val isLoadingInitial: Boolean = false,
    val isSaving: Boolean = false,
    val errorMessage: String? = null,
    val saveSuccess: Boolean = false
)

class CompanyFormViewModel(
    private val companyId: Long?,
    private val getCompanyById: GetCompanyByIdUseCase,
    private val createCompany: CreateCompanyUseCase,
    private val updateCompany: UpdateCompanyUseCase
) : ViewModel() {

    val isEditMode: Boolean get() = companyId != null

    private val _uiState = MutableStateFlow(CompanyFormUiState())
    val uiState: StateFlow<CompanyFormUiState> = _uiState.asStateFlow()

    init {
        if (companyId != null) {
            loadExisting(companyId)
        }
    }

    private fun loadExisting(id: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingInitial = true, errorMessage = null) }

            val result = getCompanyById(id)

            result.fold(
                onSuccess = { company ->
                    _uiState.update {
                        it.copy(
                            isLoadingInitial = false,
                            companyName = company.companyName,
                            cnpj = company.cnpj.orEmpty(),
                            description = company.description.orEmpty(),
                            companyEmail = company.companyEmail.orEmpty(),
                            companyPhoneNumber = company.companyPhoneNumber.orEmpty(),
                            businessArea = company.businessArea.orEmpty()
                        )
                    }
                },
                onFailure = { e ->
                    _uiState.update {
                        it.copy(
                            isLoadingInitial = false,
                            errorMessage = e.message ?: "Erro ao carregar empresa"
                        )
                    }
                }
            )
        }
    }

    fun onCompanyNameChange(value: String) = _uiState.update { it.copy(companyName = value) }
    fun onCnpjChange(value: String) = _uiState.update { it.copy(cnpj = value) }
    fun onDescriptionChange(value: String) = _uiState.update { it.copy(description = value) }
    fun onCompanyEmailChange(value: String) = _uiState.update { it.copy(companyEmail = value) }
    fun onCompanyPhoneNumberChange(value: String) = _uiState.update { it.copy(companyPhoneNumber = value) }
    fun onBusinessAreaChange(value: String) = _uiState.update { it.copy(businessArea = value) }

    fun save() {
        val state = _uiState.value

        if (state.companyName.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Nome da empresa não pode ser vazio") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true, errorMessage = null) }

            val result = if (companyId == null) {
                createCompany(
                    CompanyRequestDto(
                        companyName = state.companyName,
                        cnpj = state.cnpj.ifBlank { null },
                        description = state.description.ifBlank { null },
                        companyEmail = state.companyEmail.ifBlank { null },
                        companyPhoneNumber = state.companyPhoneNumber.ifBlank { null },
                        businessArea = state.businessArea.ifBlank { null }
                    )
                )
            } else {
                updateCompany(
                    companyId,
                    CompanyUpdateRequestDto(
                        companyName = state.companyName,
                        cnpj = state.cnpj.ifBlank { null },
                        description = state.description.ifBlank { null },
                        companyEmail = state.companyEmail.ifBlank { null },
                        companyPhoneNumber = state.companyPhoneNumber.ifBlank { null },
                        businessArea = state.businessArea.ifBlank { null }
                    )
                )
            }

            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isSaving = false, saveSuccess = true) }
                },
                onFailure = { e ->
                    _uiState.update {
                        it.copy(isSaving = false, errorMessage = e.message ?: "Erro ao salvar empresa")
                    }
                }
            )
        }
    }

    fun consumeError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}