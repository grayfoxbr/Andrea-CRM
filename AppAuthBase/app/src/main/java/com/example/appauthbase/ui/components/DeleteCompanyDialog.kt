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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appauthbase.theme.AuroraRose
import com.example.appauthbase.theme.DarkErrorBg
import com.example.appauthbase.theme.DarkGlass
import com.example.appauthbase.theme.PureWhite
import com.example.appauthbase.theme.SpecularBorderWhite
import com.example.appauthbase.theme.TextPureWhite
import com.example.appauthbase.theme.TextSilver
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
        containerColor = DarkGlass,
        modifier = Modifier.border(
            width = 1.dp,
            brush = SpecularBorderWhite,
            shape = RoundedCornerShape(22.dp)
        ),
        icon = {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(DarkErrorBg)
                    .border(1.dp, AuroraRose.copy(alpha = 0.4f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.DeleteForever,
                    contentDescription = null,
                    tint = AuroraRose,
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        title = {
            Text(
                text = "Excluir Entidade Corporativa",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black,
                color = PureWhite
            )
        },
        text = {
            Text(
                text = "Esta ação removerá permanentemente todos os registros, contatos e histórico associados a esta conta no Andrea CRM.",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSilver
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AuroraRose,
                    contentColor = PureWhite
                )
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
                    fontWeight = FontWeight.Bold,
                    color = TextSilver
                )
            }
        }
    )
}
