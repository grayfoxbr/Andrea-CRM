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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appauthbase.theme.AuroraEmerald
import com.example.appauthbase.theme.AuroraRose
import com.example.appauthbase.theme.DarkGlass
import com.example.appauthbase.theme.DarkGlassElevated
import com.example.appauthbase.theme.DarkGlassHighlight
import com.example.appauthbase.theme.JetBlack
import com.example.appauthbase.theme.PitchDark
import com.example.appauthbase.theme.PureWhite
import com.example.appauthbase.theme.SpecularBorderSubtle
import com.example.appauthbase.theme.SpecularBorderWhite
import com.example.appauthbase.theme.TextJetBlack
import com.example.appauthbase.theme.TextPureWhite
import com.example.appauthbase.theme.TextSilver
import com.example.appauthbase.theme.AppAuthBaseTheme

@Preview(showBackground = true)
@Composable
private fun HomeContentPreview() {
    AppAuthBaseTheme {
        HomeContent(
            accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkFuZHJlYSBVc2VyIiwiaWF0IjoxNTE2MjM5MDIyfQ...",
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

    AnimatedAuroraBackground(modifier = modifier) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = DarkGlass.copy(alpha = 0.85f),
                                border = androidx.compose.foundation.BorderStroke(1.dp, SpecularBorderWhite)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(7.dp)
                                            .clip(CircleShape)
                                            .background(AuroraEmerald)
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        text = "ANDREA CRM • COMMAND",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Black,
                                        letterSpacing = 1.5.sp,
                                        color = PureWhite
                                    )
                                }
                            }
                        }
                    },
                    actions = {
                        IconButton(onClick = onLogout) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                contentDescription = "Encerrar Sessão",
                                tint = AuroraRose
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = PureWhite
                    )
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                // Executive User Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Painel Executivo",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Black,
                            color = PureWhite
                        )
                        Text(
                            text = "Gestão de pipeline e clientes corporativos",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSilver
                        )
                    }

                    // Online user avatar (Crisp Black & White)
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .shadow(10.dp, CircleShape)
                            .clip(CircleShape)
                            .background(PureWhite)
                            .border(1.dp, Color.White.copy(alpha = 0.5f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = TextJetBlack,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(Modifier.height(22.dp))

                // Funnel & Pipeline Stage Widget
                LuxuryGlassCard {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        PipelineStageBar(activeCompaniesCount = 12)
                    }
                }

                Spacer(Modifier.height(16.dp))

                // Weekly Activity Chart Widget
                LuxuryGlassCard {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        MiniActivityChart()
                    }
                }

                Spacer(Modifier.height(22.dp))

                // High Contrast CTA Button (Pure White on Jet Black)
                AndreaPrimaryButton(
                    text = "Acessar Diretório de Empresas",
                    icon = Icons.Default.Business,
                    onClick = onCompaniesClick
                )

                Spacer(Modifier.height(22.dp))

                // Session Security Inspector (Collapsible)
                LuxuryGlassCard {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
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
                                        .size(34.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(PureWhite),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Key,
                                        contentDescription = null,
                                        tint = TextJetBlack,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                Spacer(Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = "Credenciais OAuth2 / JWT",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Black,
                                        color = PureWhite
                                    )
                                    Text(
                                        text = "Bearer Token Criptografado • Ativo",
                                        style = MaterialTheme.typography.bodySmall,
                                        fontSize = 11.sp,
                                        color = TextSilver
                                    )
                                }
                            }

                            Icon(
                                imageVector = if (isTokenExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = null,
                                tint = PureWhite
                            )
                        }

                        AnimatedVisibility(
                            visible = isTokenExpanded,
                            enter = expandVertically(),
                            exit = shrinkVertically()
                        ) {
                            Column(modifier = Modifier.padding(top = 16.dp)) {
                                HorizontalDivider(color = DarkGlassHighlight)
                                Spacer(Modifier.height(14.dp))

                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = JetBlack,
                                    border = androidx.compose.foundation.BorderStroke(1.dp, SpecularBorderSubtle),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = accessToken ?: "Token não disponível",
                                        style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace),
                                        color = TextSilver,
                                        maxLines = 4,
                                        modifier = Modifier.padding(14.dp)
                                    )
                                }

                                if (!accessToken.isNullOrBlank()) {
                                    Spacer(Modifier.height(12.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        AndreaOutlinedButton(
                                            text = "Copiar Token",
                                            icon = Icons.Default.ContentCopy,
                                            height = 40.dp,
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
                }

                Spacer(Modifier.height(24.dp))
            }
        }
    }
}
