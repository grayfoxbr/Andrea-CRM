package com.example.appauthbase.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appauthbase.data.remote.dto.CompanyDto
import com.example.appauthbase.theme.AppAuthBaseTheme

@Preview(showBackground = true)
@Composable
private fun CompanyCardPreview() {
    AppAuthBaseTheme {
        CompanyCard(
            company = CompanyDto(
                companyId = 1,
                companyName = "Acme Inovações Corporativas S.A.",
                businessArea = "Tecnologia",
                companyEmail = "enterprise@acme.com.br",
                companyPhoneNumber = "(11) 98765-4321"
            ),
            onClick = {},
            onDeleteClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Sem campos opcionais")
@Composable
private fun CompanyCardMinimalPreview() {
    AppAuthBaseTheme {
        CompanyCard(
            company = CompanyDto(companyId = 2, companyName = "Beta Capital Investimentos"),
            onClick = {},
            onDeleteClick = {}
        )
    }
}

@Composable
fun CompanyCard(
    company: CompanyDto,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompanyCorporateCard(
        company = company,
        onClick = onClick,
        onDeleteClick = onDeleteClick,
        modifier = modifier
    )
}
