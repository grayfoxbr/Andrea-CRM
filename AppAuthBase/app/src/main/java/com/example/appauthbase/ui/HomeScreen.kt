package com.example.appauthbase.ui

import androidx.compose.runtime.Composable
import com.example.appauthbase.ui.components.HomeContent

/**
 * HomeScreen currently has no ViewModel of its own — its state is
 * owned by the navigation graph. Kept as a thin wrapper around
 * [HomeContent] so every screen in this package follows the same
 * connector/content split.
 */
@Composable
fun HomeScreen(
    accessToken: String?,
    onLogout: () -> Unit,
    onCompaniesClick: () -> Unit
) {
    HomeContent(
        accessToken = accessToken,
        onLogout = onLogout,
        onCompaniesClick = onCompaniesClick
    )
}
