package com.example.appauthbase.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appauthbase.theme.NeoBackground
import com.example.appauthbase.theme.NeoBlack
import com.example.appauthbase.theme.NeoCardWhite
import com.example.appauthbase.theme.NeoCyan
import com.example.appauthbase.theme.NeoEmerald
import com.example.appauthbase.theme.NeoLime
import com.example.appauthbase.theme.NeoOrange
import com.example.appauthbase.theme.NeoPink
import com.example.appauthbase.theme.NeoPurple
import com.example.appauthbase.theme.NeoTextDark
import com.example.appauthbase.theme.NeoTextMuted
import com.example.appauthbase.theme.NeoYellow
import com.example.appauthbase.theme.AppAuthBaseTheme

@Preview(showBackground = true)
@Composable
private fun HomeContentPreview() {
    AppAuthBaseTheme {
        HomeContent(
            accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
            onLogout = {},
            onCompaniesClick = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    accessToken: String?,
    onLogout: () -> Unit,
    onCompaniesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var isTokenExpanded by remember { mutableStateOf(false) }
    var quickActionText by remember { mutableStateOf("") }

    Scaffold(
        containerColor = NeoBackground,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(end = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Left Settings Icon Box
                        NeoIconButton(
                            icon = Icons.Default.Settings,
                            onClick = { Toast.makeText(context, "Configurações do CRM", Toast.LENGTH_SHORT).show() },
                            size = 42.dp
                        )

                        // Right Action Buttons
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            NeoIconButton(
                                icon = Icons.Default.Search,
                                onClick = onCompaniesClick,
                                size = 42.dp
                            )
                            NeoIconButton(
                                icon = Icons.AutoMirrored.Filled.ExitToApp,
                                onClick = onLogout,
                                size = 42.dp,
                                backgroundColor = NeoPink
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NeoBackground,
                    titleContentColor = NeoBlack
                )
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(NeoBackground)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            // Punchy Hero Title (Exactly like the reference image)
            Text(
                text = "Gerencie tudo.\nFeche agora.",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Black,
                letterSpacing = (-0.5).sp,
                lineHeight = 36.sp,
                color = NeoBlack
            )

            Spacer(Modifier.height(18.dp))

            // Quick Input Action Ribbon (Input Bar + Yellow Plus Button)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Input Container
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .neoShadow(offsetX = 3.dp, offsetY = 3.dp, cornerRadius = 14.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(NeoCardWhite)
                        .border(2.5.dp, NeoBlack, RoundedCornerShape(14.dp))
                        .padding(horizontal = 14.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Tag,
                                contentDescription = null,
                                tint = NeoBlack,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = if (quickActionText.isEmpty()) "Buscar ou colar CNPJ..." else quickActionText,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (quickActionText.isEmpty()) NeoTextMuted else NeoBlack
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = null,
                            tint = NeoBlack,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(Modifier.width(10.dp))

                // Yellow Add Button
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .neoShadow(offsetX = 3.dp, offsetY = 3.dp, cornerRadius = 14.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(NeoYellow)
                        .border(2.5.dp, NeoBlack, RoundedCornerShape(14.dp))
                        .clickable(onClick = onCompaniesClick),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Adicionar",
                        tint = NeoBlack,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // Section: My Collections / Pipelines (Matching exact image layout)
            Text(
                text = "Minhas Carteiras & Pipeline",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Black,
                color = NeoBlack
            )

            Spacer(Modifier.height(14.dp))

            // 2-Column Bento Grid of Folder Cards
            // Row 1
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                NeoFolderCard(
                    title = "Empresas Ativas",
                    subtitle = "12 contas",
                    icon = Icons.Default.Folder,
                    tabColor = NeoLime,
                    onClick = onCompaniesClick,
                    modifier = Modifier.weight(1f)
                )

                NeoFolderCard(
                    title = "Em Negociação",
                    subtitle = "5 propostas",
                    icon = Icons.Default.Bolt,
                    tabColor = NeoLime,
                    onClick = onCompaniesClick,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(14.dp))

            // Row 2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                NeoFolderCard(
                    title = "Leads Quentes",
                    subtitle = "18 contatos",
                    icon = Icons.Default.MenuBook,
                    tabColor = NeoYellow,
                    onClick = onCompaniesClick,
                    modifier = Modifier.weight(1f)
                )

                NeoFolderCard(
                    title = "Clientes VIP",
                    subtitle = "7 contratos",
                    icon = Icons.Default.Coffee,
                    tabColor = NeoLime,
                    onClick = onCompaniesClick,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(14.dp))

            // Row 3
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                NeoFolderCard(
                    title = "Propostas",
                    subtitle = "13 enviadas",
                    icon = Icons.Default.Flight,
                    tabColor = NeoPink,
                    onClick = onCompaniesClick,
                    modifier = Modifier.weight(1f)
                )

                NeoFolderCard(
                    title = "Novos Contatos",
                    subtitle = "7 contas",
                    icon = Icons.Default.Bookmark,
                    tabColor = NeoPurple,
                    onClick = onCompaniesClick,
                    modifier = Modifier.weight(1f),
                    isNew = true
                )
            }

            Spacer(Modifier.height(24.dp))

            // Primary Access Button
            AndreaPrimaryButton(
                text = "Acessar Diretório de Empresas",
                icon = Icons.Default.Business,
                onClick = onCompaniesClick
            )

            Spacer(Modifier.height(20.dp))

            // Session OAuth2 Inspector Card
            LuxuryGlassCard {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isTokenExpanded = !isTokenExpanded },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(NeoYellow)
                                    .border(2.dp, NeoBlack, RoundedCornerShape(10.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Key,
                                    contentDescription = null,
                                    tint = NeoBlack,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Credenciais OAuth2 / JWT",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Black,
                                    color = NeoBlack
                                )
                                Text(
                                    text = "Bearer Token Ativo • OpenID Connect",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    color = NeoTextMuted
                                )
                            }
                        }

                        Icon(
                            imageVector = if (isTokenExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = null,
                            tint = NeoBlack
                        )
                    }

                    AnimatedVisibility(
                        visible = isTokenExpanded,
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {
                        Column(modifier = Modifier.padding(top = 16.dp)) {
                            HorizontalDivider(color = NeoBlack, thickness = 2.dp)
                            Spacer(Modifier.height(14.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color(0xFFF4F4F5))
                                    .border(2.dp, NeoBlack, RoundedCornerShape(10.dp))
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = accessToken ?: "Token não disponível",
                                    style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace),
                                    fontWeight = FontWeight.Bold,
                                    color = NeoBlack,
                                    maxLines = 4
                                )
                            }

                            if (!accessToken.isNullOrBlank()) {
                                Spacer(Modifier.height(12.dp))
                                AndreaOutlinedButton(
                                    text = "Copiar Token",
                                    icon = Icons.Default.ContentCopy,
                                    height = 42.dp,
                                    onClick = {
                                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                        val clip = ClipData.newPlainText("Access Token", accessToken)
                                        clipboard.setPrimaryClip(clip)
                                        Toast.makeText(context, "Token copiado com sucesso!", Toast.LENGTH_SHORT).show()
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}
