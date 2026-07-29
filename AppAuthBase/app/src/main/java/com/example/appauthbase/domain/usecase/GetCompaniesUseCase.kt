package com.example.appauthbase.domain.usecase.company

import com.example.appauthbase.data.remote.dto.CompanyDto
import com.example.appauthbase.domain.repository.CompanyRepository

class GetCompaniesUseCase(
    private val repo: CompanyRepository
) {
    suspend operator fun invoke(): Result<List<CompanyDto>> = repo.getAll()
}