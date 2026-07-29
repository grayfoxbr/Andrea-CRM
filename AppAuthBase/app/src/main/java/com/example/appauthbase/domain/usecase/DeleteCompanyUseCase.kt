package com.example.appauthbase.domain.usecase.company

import com.example.appauthbase.domain.repository.CompanyRepository

class DeleteCompanyUseCase(
    private val repo: CompanyRepository
) {
    suspend operator fun invoke(id: Long): Result<Unit> = repo.delete(id)
}