package com.example.appauthbase.domain.usecase.company

import com.example.appauthbase.data.remote.dto.CompanyDto
import com.example.appauthbase.domain.repository.CompanyRepository

class GetCompanyByIdUseCase(
    private val repo: CompanyRepository
) {
    suspend operator fun invoke(id: Long): Result<CompanyDto> = repo.getById(id)
}