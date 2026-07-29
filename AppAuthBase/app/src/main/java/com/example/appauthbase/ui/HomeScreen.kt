package com.example.appauthbase.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    onCompaniesClick: () -> Unit,
    onNewCompanyClick: () -> Unit
) {

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text("AppAuth Base")
                },
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

            verticalArrangement = Arrangement.spacedBy(20.dp)

        ) {

            Text(
                "Bem-vindo!",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                "Gerencie as empresas cadastradas através das opções abaixo.",
                style = MaterialTheme.typography.bodyLarge
            )

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        "Empresas",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        "Cadastre, consulte, edite e exclua empresas."
                    )

                    Spacer(Modifier.height(20.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onCompaniesClick
                    ) {
                        Text("Visualizar Empresas")
                    }

                    Spacer(Modifier.height(12.dp))

                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onNewCompanyClick
                    ) {
                        Text("Nova Empresa")
                    }
                }
            }
        }
    }
}