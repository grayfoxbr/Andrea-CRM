package com.example.appauthbase.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LeadDto(
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val companyName: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val status: String? = null,
    val leadSource: String? = null,
    val assignedTo: String? = null
)
