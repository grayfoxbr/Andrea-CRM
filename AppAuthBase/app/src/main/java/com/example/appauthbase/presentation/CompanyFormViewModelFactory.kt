package com.example.appauthbase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appauthbase.domain.usecase.company.CompanyUseCaseProvider

class CompanyFormViewModelFactory(
    private val companyId: Long?
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(CompanyFormViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return CompanyFormViewModel(
                companyId = companyId,
                getCompanyById = CompanyUseCaseProvider.getCompanyById,
                createCompany = CompanyUseCaseProvider.createCompany,
                updateCompany = CompanyUseCaseProvider.updateCompany
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel: ${modelClass.name}")
    }
}