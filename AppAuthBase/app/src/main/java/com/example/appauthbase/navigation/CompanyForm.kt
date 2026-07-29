package com.example.appauthbase.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * companyId == null  -> modo criação
 * companyId != null  -> modo edição
 */
@Serializable
data class CompanyForm(val companyId: Long? = null) : NavKey