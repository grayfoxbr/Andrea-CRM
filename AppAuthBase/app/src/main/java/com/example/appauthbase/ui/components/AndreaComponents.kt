package com.example.appauthbase.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appauthbase.data.remote.dto.CompanyDto
import com.example.appauthbase.theme.AuroraAmber
import com.example.appauthbase.theme.AuroraCyan
import com.example.appauthbase.theme.AuroraEmerald
import com.example.appauthbase.theme.AuroraIndigo
import com.example.appauthbase.theme.AuroraRose
import com.example.appauthbase.theme.AuroraShineGradient
import com.example.appauthbase.theme.AuroraViolet
import com.example.appauthbase.theme.CorporatePassMonochrome
import com.example.appauthbase.theme.DarkErrorBg
import com.example.appauthbase.theme.DarkErrorFg
import com.example.appauthbase.theme.DarkGlass
import com.example.appauthbase.theme.DarkGlassElevated
import com.example.appauthbase.theme.DarkGlassHighlight
import com.example.appauthbase.theme.DarkSuccessBg
import com.example.appauthbase.theme.DarkSuccessFg
import com.example.appauthbase.theme.DarkWarningBg
import com.example.appauthbase.theme.DarkWarningFg
import com.example.appauthbase.theme.JetBlack
import com.example.appauthbase.theme.PitchDark
import com.example.appauthbase.theme.PureWhite
import com.example.appauthbase.theme.SpecularBorderSubtle
import com.example.appauthbase.theme.SpecularBorderWhite
import com.example.appauthbase.theme.TextJetBlack
import com.example.appauthbase.theme.TextMuted
import com.example.appauthbase.theme.TextPearl
import com.example.appauthbase.theme.TextPureWhite
import com.example.appauthbase.theme.TextSilver
import com.example.appauthbase.theme.WhiteToSilverGradient
import kotlin.math.abs

/**
 * Animated Color-Shifting Aurora Background (Living Fluid Mesh Glow)
 */
@Composable
fun AnimatedAuroraBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "auroraTransition")

    val t1 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 9000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "t1"
    )

    val t2 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 13000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "t2"
    )

    val t3 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 11000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "t3"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(JetBlack)
    ) {
        // GPU Canvas Drawing of Fluid Glowing Orbs
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Orb 1: Electric Cyan shifting horizontally & vertically
            val c1X = w * (0.15f + 0.65f * t1)
            val c1Y = h * (0.10f + 0.25f * t2)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        AuroraCyan.copy(alpha = 0.24f),
                        AuroraCyan.copy(alpha = 0.08f),
                        Color.Transparent
                    ),
                    center = Offset(c1X, c1Y),
                    radius = w * 0.85f
                )
            )

            // Orb 2: Royal Violet shifting opposite
            val c2X = w * (0.85f - 0.60f * t2)
            val c2Y = h * (0.35f + 0.35f * t3)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        AuroraViolet.copy(alpha = 0.22f),
                        AuroraViolet.copy(alpha = 0.06f),
                        Color.Transparent
                    ),
                    center = Offset(c2X, c2Y),
                    radius = w * 0.90f
                )
            )

            // Orb 3: Emerald & Indigo Pulse at lower section
            val c3X = w * (0.30f + 0.45f * (1f - t3))
            val c3Y = h * (0.75f - 0.25f * t1)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        AuroraEmerald.copy(alpha = 0.18f),
                        AuroraIndigo.copy(alpha = 0.06f),
                        Color.Transparent
                    ),
                    center = Offset(c3X, c3Y),
                    radius = w * 0.80f
                )
            )
        }

        content()
    }
}

/**
 * Luxury Frosted Glass Card with 1px Specular White Highlight Border
 */
@Composable
fun LuxuryGlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 22.dp,
    content: @Composable () -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val borderBrush = if (isDark) SpecularBorderWhite else SpecularBorderSubtle
    val containerBg = if (isDark) DarkGlass.copy(alpha = 0.82f) else Color.White.copy(alpha = 0.92f)

    Box(
        modifier = modifier
            .shadow(
                elevation = 14.dp,
                shape = RoundedCornerShape(cornerRadius),
                ambientColor = Color.Black.copy(alpha = 0.7f),
                spotColor = AuroraCyan.copy(alpha = 0.15f)
            )
            .clip(RoundedCornerShape(cornerRadius))
            .background(containerBg)
            .border(
                width = 1.dp,
                brush = borderBrush,
                shape = RoundedCornerShape(cornerRadius)
            )
    ) {
        content()
    }
}

/**
 * High-Contrast Luxury Brand Logo
 */
@Composable
fun BespokeLogo(
    modifier: Modifier = Modifier,
    isLarge: Boolean = false
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Monogram Badge with Specular Border & Shimmer
        Box(
            modifier = Modifier
                .size(if (isLarge) 68.dp else 50.dp)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(20.dp),
                    ambientColor = Color.White.copy(alpha = 0.35f),
                    spotColor = AuroraCyan.copy(alpha = 0.5f)
                )
                .clip(RoundedCornerShape(20.dp))
                .background(JetBlack)
                .border(
                    width = 1.5.dp,
                    brush = SpecularBorderWhite,
                    shape = RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Domain,
                contentDescription = "Andrea CRM Logo",
                tint = PureWhite,
                modifier = Modifier.size(if (isLarge) 34.dp else 26.dp)
            )
        }

        Spacer(Modifier.height(14.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "ANDREA",
                style = if (isLarge) MaterialTheme.typography.headlineMedium else MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.5.sp,
                color = PureWhite
            )
            Spacer(Modifier.width(8.dp))
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = PureWhite,
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.8f))
            ) {
                Text(
                    text = "CRM",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 2.sp,
                    color = TextJetBlack,
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.5.dp)
                )
            }
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = "Enterprise Client Intelligence Platform",
            style = MaterialTheme.typography.bodySmall,
            color = TextSilver,
            letterSpacing = 0.5.sp
        )
    }
}

/**
 * Pure White High-Contrast Tactical Primary Button
 */
@Composable
fun AndreaPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    icon: ImageVector? = null,
    gradient: Brush = WhiteToSilverGradient,
    height: Dp = 52.dp
) {
    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .shadow(
                elevation = if (enabled) 12.dp else 0.dp,
                shape = RoundedCornerShape(14.dp),
                ambientColor = Color.White.copy(alpha = 0.4f),
                spotColor = Color.White.copy(alpha = 0.4f)
            ),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.White.copy(alpha = 0.12f)
        ),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(
                    if (enabled) gradient else Brush.linearGradient(
                        listOf(Color.White.copy(alpha = 0.2f), Color.White.copy(alpha = 0.2f))
                    )
                )
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(14.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = TextJetBlack,
                    strokeWidth = 2.5.dp
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (icon != null) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = TextJetBlack,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                    }
                    Text(
                        text = text,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.3.sp,
                        color = TextJetBlack
                    )
                }
            }
        }
    }
}

/**
 * Jet-Black Frosted Secondary Button with 1px Specular Border
 */
@Composable
fun AndreaOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    height: Dp = 48.dp
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = DarkGlass.copy(alpha = 0.75f),
            contentColor = PureWhite
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            brush = SpecularBorderWhite
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = PureWhite,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = PureWhite
            )
        }
    }
}

/**
 * Luxury Linear/Obsidian Text Field
 */
@Composable
fun AndreaTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    isPassword: Boolean = false,
    singleLine: Boolean = true,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label, fontWeight = FontWeight.SemiBold) },
            placeholder = placeholder?.let { { Text(it, color = TextMuted) } },
            enabled = enabled,
            singleLine = singleLine,
            minLines = minLines,
            isError = isError,
            shape = RoundedCornerShape(14.dp),
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            leadingIcon = leadingIcon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = if (isError) AuroraRose else PureWhite.copy(alpha = 0.85f),
                        modifier = Modifier.size(19.dp)
                    )
                }
            },
            trailingIcon = {
                if (isPassword) {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = if (passwordVisible) "Ocultar senha" else "Mostrar senha",
                            tint = TextSilver
                        )
                    }
                } else if (value.isNotEmpty() && enabled) {
                    IconButton(onClick = { onValueChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Limpar texto",
                            tint = TextSilver,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PureWhite,
                unfocusedBorderColor = DarkGlassHighlight,
                focusedContainerColor = DarkGlassElevated.copy(alpha = 0.85f),
                unfocusedContainerColor = DarkGlass.copy(alpha = 0.70f),
                disabledContainerColor = JetBlack,
                focusedTextColor = TextPureWhite,
                unfocusedTextColor = TextPearl,
                focusedLabelColor = PureWhite,
                unfocusedLabelColor = TextSilver
            ),
            modifier = Modifier.fillMaxWidth()
        )

        if (isError && errorMessage != null) {
            Spacer(Modifier.height(4.dp))
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodySmall,
                color = AuroraRose,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

/**
 * Pipeline Funnel Bar Widget
 */
@Composable
fun PipelineStageBar(
    activeCompaniesCount: Int,
    modifier: Modifier = Modifier
) {
    val stages = listOf(
        Triple("Leads", 0.35f, AuroraCyan),
        Triple("Propostas", 0.25f, AuroraIndigo),
        Triple("Negociação", 0.20f, AuroraViolet),
        Triple("Fechado", 0.20f, AuroraEmerald)
    )

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "PIPELINE DE VENDAS",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.5.sp,
                color = PureWhite
            )

            Surface(
                shape = RoundedCornerShape(50),
                color = AuroraEmerald.copy(alpha = 0.15f),
                border = androidx.compose.foundation.BorderStroke(1.dp, AuroraEmerald.copy(alpha = 0.4f))
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(AuroraEmerald)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = "$activeCompaniesCount ativas",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Black,
                        color = AuroraEmerald,
                        fontSize = 11.sp
                    )
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // Progress Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            stages.forEach { (_, weight, color) ->
                Box(
                    modifier = Modifier
                        .weight(weight)
                        .fillMaxSize()
                        .background(color)
                )
            }
        }

        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            stages.forEach { (label, _, color) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(color)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 10.sp,
                        color = TextSilver
                    )
                }
            }
        }
    }
}

/**
 * Mini Activity Sparkline Chart
 */
@Composable
fun MiniActivityChart(
    modifier: Modifier = Modifier
) {
    val barValues = listOf(0.35f, 0.65f, 0.50f, 0.90f, 0.60f, 0.85f, 1.0f)
    val days = listOf("Seg", "Ter", "Qua", "Qui", "Sex", "Sab", "Dom")

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ATIVIDADE EM TEMPO REAL",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.5.sp,
                color = PureWhite
            )
            Text(
                text = "+28% esta semana",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = AuroraEmerald
            )
        }

        Spacer(Modifier.height(14.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            barValues.forEachIndexed { index, value ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .width(18.dp)
                            .height((40 * value).dp)
                            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                            .background(
                                if (index == barValues.lastIndex) Brush.linearGradient(listOf(PureWhite, PureWhite))
                                else Brush.verticalGradient(
                                    listOf(Color.White.copy(alpha = 0.5f), Color.White.copy(alpha = 0.12f))
                                )
                            )
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = days[index],
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 9.sp,
                        color = TextSilver
                    )
                }
            }
        }
    }
}

/**
 * Holographic Black & White Corporate Pass
 */
@Composable
fun CorporatePassHologram(
    companyName: String,
    cnpj: String?,
    businessArea: String?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(22.dp),
                ambientColor = Color.White.copy(alpha = 0.2f),
                spotColor = AuroraCyan.copy(alpha = 0.35f)
            ),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(CorporatePassMonochrome)
                .border(
                    width = 1.2.dp,
                    brush = SpecularBorderWhite,
                    shape = RoundedCornerShape(22.dp)
                )
                .padding(22.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(PureWhite),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = companyName.take(1).uppercase().ifEmpty { "A" },
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Black,
                                color = TextJetBlack
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "CORPORATE PASS",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.5.sp,
                                color = PureWhite
                            )
                            Text(
                                text = "ENTIDADE AUTENTICADA",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 9.sp,
                                color = AuroraEmerald
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = null,
                        tint = PureWhite,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(Modifier.height(20.dp))

                Text(
                    text = companyName.ifEmpty { "Razão Social da Empresa" },
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Black,
                    color = PureWhite,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (!cnpj.isNullOrBlank()) "CNPJ: $cnpj" else "CNPJ: Não informado",
                        style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace),
                        color = TextSilver
                    )

                    if (!businessArea.isNullOrBlank()) {
                        CategoryBadge(area = businessArea)
                    }
                }
            }
        }
    }
}

/**
 * Fintech-style Company Card for Directory
 */
@Composable
fun CompanyCorporateCard(
    company: CompanyDto,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LuxuryGlassCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Monogram Avatar
            CompanyAvatar(
                name = company.companyName,
                size = 46.dp
            )

            Spacer(Modifier.width(14.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = company.companyName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = PureWhite,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f, fill = false)
                    )

                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(AuroraEmerald)
                    )
                }

                if (!company.businessArea.isNullOrBlank()) {
                    Spacer(Modifier.height(6.dp))
                    CategoryBadge(area = company.businessArea)
                }

                if (!company.companyEmail.isNullOrBlank() || !company.companyPhoneNumber.isNullOrBlank()) {
                    Spacer(Modifier.height(8.dp))
                }

                company.companyEmail?.takeIf { it.isNotBlank() }?.let { email ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = PureWhite.copy(alpha = 0.7f),
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = email,
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSilver,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                company.companyPhoneNumber?.takeIf { it.isNotBlank() }?.let { phone ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = null,
                            tint = AuroraEmerald,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = phone,
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSilver,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            // Action delete button
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(DarkErrorBg)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Excluir empresa",
                    tint = AuroraRose,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

/**
 * Company Initial Avatar
 */
@Composable
fun CompanyAvatar(
    name: String,
    modifier: Modifier = Modifier,
    size: Dp = 46.dp
) {
    val initial = name.trim().take(1).uppercase().ifEmpty { "A" }

    Box(
        modifier = modifier
            .size(size)
            .shadow(8.dp, RoundedCornerShape(14.dp))
            .clip(RoundedCornerShape(14.dp))
            .background(PureWhite)
            .border(1.dp, Color.White.copy(alpha = 0.4f), RoundedCornerShape(14.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initial,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            color = TextJetBlack
        )
    }
}

/**
 * Category badge with high-contrast luxury styling
 */
@Composable
fun CategoryBadge(
    area: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = DarkGlassElevated,
        border = androidx.compose.foundation.BorderStroke(1.dp, SpecularBorderSubtle),
        modifier = modifier
    ) {
        Text(
            text = area,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = PureWhite,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
    }
}

/**
 * Status Banner Alert
 */
@Composable
fun AndreaStatusBanner(
    message: String,
    type: BannerType = BannerType.ERROR,
    modifier: Modifier = Modifier
) {
    val (bg, fg, icon) = when (type) {
        BannerType.ERROR -> Triple(DarkErrorBg, DarkErrorFg, Icons.Default.Error)
        BannerType.SUCCESS -> Triple(DarkSuccessBg, DarkSuccessFg, Icons.Default.CheckCircle)
        BannerType.WARNING -> Triple(DarkWarningBg, DarkWarningFg, Icons.Default.Warning)
        BannerType.INFO -> Triple(DarkGlassElevated, PureWhite, Icons.Default.Info)
    }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = bg,
        border = androidx.compose.foundation.BorderStroke(1.dp, fg.copy(alpha = 0.35f)),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = fg,
                modifier = Modifier.size(19.dp)
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = fg
            )
        }
    }
}

enum class BannerType {
    ERROR, SUCCESS, WARNING, INFO
}
