package com.example.appauthbase.domain.usecase.company

import com.example.appauthbase.data.remote.ResourceApiProvider
import com.example.appauthbase.data.repository.CompanyRepositoryImpl

/**
 * Ponto único de montagem do repository + use cases de Company.
 * Use em uma ViewModelFactory (mesmo padrão de AuthViewModelFactory),
 * ex.:
 *
 *     class CompanyViewModelFactory : ViewModelProvider.Factory {
 *         override fun <T : ViewModel> create(modelClass: Class<T>): T {
 *             val getAll = CompanyUseCaseProvider.getCompanies
 *             val create = CompanyUseCaseProvider.createCompany
 *             ...
 *             return CompanyViewModel(getAll, create, ...) as T
 *         }
 *     }
 */
object CompanyUseCaseProvider {

    private val repository by lazy {
        CompanyRepositoryImpl(ResourceApiProvider.companyApi)
    }

    val getCompanies: GetCompaniesUseCase by lazy { GetCompaniesUseCase(repository) }
    val getCompanyById: GetCompanyByIdUseCase by lazy { GetCompanyByIdUseCase(repository) }
    val createCompany: CreateCompanyUseCase by lazy { CreateCompanyUseCase(repository) }
    val updateCompany: UpdateCompanyUseCase by lazy { UpdateCompanyUseCase(repository) }
    val deleteCompany: DeleteCompanyUseCase by lazy { DeleteCompanyUseCase(repository) }
}