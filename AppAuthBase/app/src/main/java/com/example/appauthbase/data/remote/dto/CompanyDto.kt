package com.example.appauthbase.data.remote.dto

data class CompanyDto(
    val companyId: Long? = null,
    val companyName: String,
    val cnpj: String? = null,
    val description: String? = null,
    val companyEmail: String? = null,
    val companyPhoneNumber: String? = null,
    val businessArea: String? = null
)