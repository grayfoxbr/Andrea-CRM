package com.example.appauthbase.data.remote

import com.example.appauthbase.data.remote.dto.CompanyDto
import com.example.appauthbase.data.remote.dto.CompanyRequestDto
import com.example.appauthbase.data.remote.dto.CompanyUpdateRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Endpoints protegidos de Companies no andrea-resource-api.
 * Requer JWT Bearer — anexado automaticamente pelo AuthInterceptor
 * quando chamado via ResourceApiProvider.companyApi.
 */
interface CompanyApi {

    @GET("api/companies")
    suspend fun getAll(): Response<List<CompanyDto>>

    @GET("api/companies/{id}")
    suspend fun getById(@Path("id") id: Long): Response<CompanyDto>

    @POST("api/companies")
    suspend fun create(@Body request: CompanyRequestDto): Response<CompanyDto>

    @PUT("api/companies/{id}")
    suspend fun update(
        @Path("id") id: Long,
        @Body request: CompanyUpdateRequestDto
    ): Response<CompanyDto>

    @DELETE("api/companies/{id}")
    suspend fun delete(@Path("id") id: Long): Response<Unit>
}