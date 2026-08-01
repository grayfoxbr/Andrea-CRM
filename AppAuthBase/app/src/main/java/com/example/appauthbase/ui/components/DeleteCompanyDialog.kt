package com.example.appauthbase.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

/**
 * Confirmation dialog shown before deleting a company.
 */

import androidx.compose.ui.tooling.preview.Preview
import com.example.appauthbase.theme.AppAuthBaseTheme

@Preview(showBackground = true)
@Composable
private fun DeleteCompanyDialogPreview() {
    AppAuthBaseTheme {
        DeleteCompanyDialog(onConfirm = {}, onDismiss = {})
    }
}
@Composable
fun DeleteCompanyDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Excluir empresa") },
        text = { Text("Tem certeza que deseja excluir esta empresa? Essa ação não pode ser desfeita.") },
        confirmButton = {
            TextButton(onClick = onConfirm) { Text("Excluir") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}
