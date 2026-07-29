package com.example.appauthbase.domain.usecase.company

import com.example.appauthbase.data.remote.dto.CompanyDto
import com.example.appauthbase.data.remote.dto.CompanyRequestDto
import com.example.appauthbase.domain.repository.CompanyRepository

class CreateCompanyUseCase(
    private val repo: CompanyRepository
) {
    suspend operator fun invoke(request: CompanyRequestDto): Result<CompanyDto> {
        if (request.companyName.isBlank()) {
            return Result.failure(IllegalArgumentException("Nome da empresa não pode ser vazio"))
        }
        return repo.create(request)
    }
}