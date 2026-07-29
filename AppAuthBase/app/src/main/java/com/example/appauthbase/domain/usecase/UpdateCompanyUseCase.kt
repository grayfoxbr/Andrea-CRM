package com.example.appauthbase.domain.usecase.company

import com.example.appauthbase.data.remote.dto.CompanyDto
import com.example.appauthbase.data.remote.dto.CompanyUpdateRequestDto
import com.example.appauthbase.domain.repository.CompanyRepository

class UpdateCompanyUseCase(
    private val repo: CompanyRepository
) {
    suspend operator fun invoke(id: Long, request: CompanyUpdateRequestDto): Result<CompanyDto> {
        if (request.companyName.isBlank()) {
            return Result.failure(IllegalArgumentException("Nome da empresa não pode ser vazio"))
        }
        return repo.update(id, request)
    }
}