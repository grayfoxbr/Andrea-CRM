package com.example.appauthbase.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appauthbase.theme.NeoBlack
import com.example.appauthbase.theme.NeoCardWhite
import com.example.appauthbase.theme.NeoErrorBg
import com.example.appauthbase.theme.NeoPink
import com.example.appauthbase.theme.NeoTextDark
import com.example.appauthbase.theme.NeoTextMuted
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
        shape = RoundedCornerShape(22.dp),
        containerColor = NeoCardWhite,
        modifier = Modifier
            .neoShadow(offsetX = 5.dp, offsetY = 5.dp, cornerRadius = 22.dp)
            .border(2.5.dp, NeoBlack, RoundedCornerShape(22.dp)),
        icon = {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(NeoPink)
                    .border(2.5.dp, NeoBlack, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.DeleteForever,
                    contentDescription = null,
                    tint = NeoBlack,
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        title = {
            Text(
                text = "Excluir Entidade",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black,
                color = NeoBlack
            )
        },
        text = {
            Text(
                text = "Esta ação removerá permanentemente todos os registros, contatos e histórico associados a esta conta no Andrea CRM.",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = NeoTextMuted
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeoPink,
                    contentColor = NeoBlack
                ),
                border = androidx.compose.foundation.BorderStroke(2.dp, NeoBlack)
            ) {
                Text(
                    text = "Excluir Definitivamente",
                    fontWeight = FontWeight.Black
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Cancelar",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Black,
                    color = NeoBlack
                )
            }
        }
    )
}
