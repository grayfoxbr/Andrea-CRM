package com.example.appauthbase.domain.repository

import com.example.appauthbase.data.remote.dto.CompanyDto
import com.example.appauthbase.data.remote.dto.CompanyRequestDto
import com.example.appauthbase.data.remote.dto.CompanyUpdateRequestDto

interface CompanyRepository {

    suspend fun getAll(): Result<List<CompanyDto>>

    suspend fun getById(id: Long): Result<CompanyDto>

    suspend fun create(request: CompanyRequestDto): Result<CompanyDto>

    suspend fun update(id: Long, request: CompanyUpdateRequestDto): Result<CompanyDto>

    suspend fun delete(id: Long): Result<Unit>
}