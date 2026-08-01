package com.example.appauthbase.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Pure UI for the home screen. Takes plain data and callbacks only —
 * it has no dependency on any ViewModel.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    accessToken: String?,
    onLogout: () -> Unit,
    onCompaniesClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                actions = {
                    TextButton(onClick = onLogout) {
                        Text("Sair")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Usuário autenticado",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(text = accessToken ?: "Token indisponível")

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = onCompaniesClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Empresas")
            }
        }
    }
}
