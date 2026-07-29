package com.example.appauthbase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appauthbase.domain.usecase.company.CompanyUseCaseProvider

class CompanyListViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(CompanyListViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return CompanyListViewModel(
                getCompanies = CompanyUseCaseProvider.getCompanies,
                deleteCompany = CompanyUseCaseProvider.deleteCompany
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel: ${modelClass.name}")
    }
}