package com.example.appauthbase.data.repository

import com.example.appauthbase.data.remote.CompanyApi
import com.example.appauthbase.data.remote.dto.CompanyDto
import com.example.appauthbase.data.remote.dto.CompanyRequestDto
import com.example.appauthbase.data.remote.dto.CompanyUpdateRequestDto
import com.example.appauthbase.domain.repository.CompanyRepository

class CompanyRepositoryImpl(
    private val api: CompanyApi
) : CompanyRepository {

    override suspend fun getAll(): Result<List<CompanyDto>> {
        return try {
            val response = api.getAll()
            if (response.isSuccessful) {
                Result.success(response.body().orEmpty())
            } else {
                Result.failure(errorFor(response.code(), response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Falha de conexão: ${e.message}"))
        }
    }

    override suspend fun getById(id: Long): Result<CompanyDto> {
        return try {
            val response = api.getById(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(errorFor(response.code(), response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Falha de conexão: ${e.message}"))
        }
    }

    override suspend fun create(request: CompanyRequestDto): Result<CompanyDto> {
        return try {
            val response = api.create(request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(errorFor(response.code(), response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Falha de conexão: ${e.message}"))
        }
    }

    override suspend fun update(id: Long, request: CompanyUpdateRequestDto): Result<CompanyDto> {
        return try {
            val response = api.update(id, request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(errorFor(response.code(), response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Falha de conexão: ${e.message}"))
        }
    }

    override suspend fun delete(id: Long): Result<Unit> {
        return try {
            val response = api.delete(id)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(errorFor(response.code(), response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Falha de conexão: ${e.message}"))
        }
    }

    /**
     * 401/403 geralmente indicam token ausente, expirado ou sem
     * permissão (ROLE incorreta) — mensagem diferenciada ajuda a
     * distinguir isso de outros erros de validação (400) ou
     * conflitos de negócio.
     */
    private fun errorFor(code: Int, body: String?): Exception {
        return when (code) {
            401, 403 -> Exception("Não autorizado (token ausente, expirado ou sem permissão)")
            else -> Exception("Erro $code: ${body ?: "desconhecido"}")
        }
    }
}