package com.example.appauthbase.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Shield
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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
import com.example.appauthbase.theme.AmberGlow
import com.example.appauthbase.theme.AndreaElectricGradient
import com.example.appauthbase.theme.AndreaGlassBorderDark
import com.example.appauthbase.theme.AndreaGlassBorderLight
import com.example.appauthbase.theme.AndreaPassGradient
import com.example.appauthbase.theme.AndreaPassLightGradient
import com.example.appauthbase.theme.AndreaPrimaryGradient
import com.example.appauthbase.theme.DarkErrorBg
import com.example.appauthbase.theme.DarkErrorFg
import com.example.appauthbase.theme.DarkSuccessBg
import com.example.appauthbase.theme.DarkSuccessFg
import com.example.appauthbase.theme.DarkWarningBg
import com.example.appauthbase.theme.DarkWarningFg
import com.example.appauthbase.theme.ElectricCyan
import com.example.appauthbase.theme.EmeraldGlow
import com.example.appauthbase.theme.EmeraldPulse
import com.example.appauthbase.theme.LightErrorBg
import com.example.appauthbase.theme.LightErrorFg
import com.example.appauthbase.theme.LightSuccessBg
import com.example.appauthbase.theme.LightSuccessFg
import com.example.appauthbase.theme.LightWarningBg
import com.example.appauthbase.theme.LightWarningFg
import com.example.appauthbase.theme.ObsidianBase
import com.example.appauthbase.theme.ObsidianBorder
import com.example.appauthbase.theme.ObsidianElevated
import com.example.appauthbase.theme.ObsidianSurface
import com.example.appauthbase.theme.RosePulse
import com.example.appauthbase.theme.RoyalSapphire
import com.example.appauthbase.theme.RoyalSapphireDark
import com.example.appauthbase.theme.TextWhiteHigh
import com.example.appauthbase.theme.TextWhiteLow
import com.example.appauthbase.theme.TextWhiteMedium
import com.example.appauthbase.theme.VioletVibrant
import kotlin.math.abs

/**
 * Luxury Glass Card with top specular highlight and frosted surface
 */
@Composable
fun LuxuryGlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 22.dp,
    content: @Composable () -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val borderBrush = if (isDark) AndreaGlassBorderDark else AndreaGlassBorderLight
    val containerBg = if (isDark) ObsidianElevated.copy(alpha = 0.90f) else Color.White

    Box(
        modifier = modifier
            .shadow(
                elevation = if (isDark) 8.dp else 4.dp,
                shape = RoundedCornerShape(cornerRadius),
                ambientColor = if (isDark) Color.Black.copy(alpha = 0.6f) else Color(0xFF6366F1).copy(alpha = 0.08f),
                spotColor = if (isDark) Color.Black.copy(alpha = 0.6f) else Color(0xFF6366F1).copy(alpha = 0.08f)
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
 * Bespoke Geometric Brand Monogram Logo
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
        // Monogram Icon Box with Specular Border
        Box(
            modifier = Modifier
                .size(if (isLarge) 64.dp else 48.dp)
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(18.dp),
                    ambientColor = ElectricCyan.copy(alpha = 0.4f),
                    spotColor = RoyalSapphire.copy(alpha = 0.4f)
                )
                .clip(RoundedCornerShape(18.dp))
                .background(AndreaElectricGradient)
                .border(
                    width = 1.5.dp,
                    color = Color.White.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(18.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Domain,
                contentDescription = "Andrea CRM Logo",
                tint = Color.White,
                modifier = Modifier.size(if (isLarge) 34.dp else 26.dp)
            )
        }

        Spacer(Modifier.height(14.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "ANDREA",
                style = if (isLarge) MaterialTheme.typography.headlineMedium else MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.width(6.dp))
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = ElectricCyan.copy(alpha = 0.15f),
                border = androidx.compose.foundation.BorderStroke(1.dp, ElectricCyan.copy(alpha = 0.4f))
            ) {
                Text(
                    text = "CRM",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.5.sp,
                    color = ElectricCyan,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = "Enterprise Client Relationship Management",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            letterSpacing = 0.3.sp
        )
    }
}

/**
 * Top-tier Action Button with 1px top specular highlight
 */
@Composable
fun AndreaPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    icon: ImageVector? = null,
    gradient: Brush = AndreaElectricGradient,
    height: Dp = 52.dp
) {
    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .shadow(
                elevation = if (enabled) 8.dp else 0.dp,
                shape = RoundedCornerShape(14.dp),
                ambientColor = ElectricCyan.copy(alpha = 0.35f),
                spotColor = RoyalSapphire.copy(alpha = 0.35f)
            ),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
        ),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(
                    if (enabled) gradient else Brush.linearGradient(
                        listOf(
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                        )
                    )
                )
                .border(
                    width = 1.dp,
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.White.copy(alpha = 0.35f),
                            Color.White.copy(alpha = 0.05f)
                        )
                    ),
                    shape = RoundedCornerShape(14.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White,
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
                            tint = Color.White,
                            modifier = Modifier.size(19.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                    }
                    Text(
                        text = text,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.2.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

/**
 * Modern Outlined Button with subtle specular outline
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
    val isDark = isSystemInDarkTheme()
    val bgColor = if (isDark) ObsidianSurface.copy(alpha = 0.7f) else MaterialTheme.colorScheme.surface

    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = bgColor,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            brush = if (isDark) AndreaGlassBorderDark else AndreaGlassBorderLight
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
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

/**
 * Linear-styled input field with glowing focus border and frosted container
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
    val isDark = isSystemInDarkTheme()

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label, fontWeight = FontWeight.Medium) },
            placeholder = placeholder?.let { { Text(it, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.45f)) } },
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
                        tint = if (isError) MaterialTheme.colorScheme.error else ElectricCyan.copy(alpha = 0.85f),
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
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                        )
                    }
                } else if (value.isNotEmpty() && enabled) {
                    IconButton(onClick = { onValueChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Limpar texto",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ElectricCyan,
                unfocusedBorderColor = if (isDark) ObsidianBorder else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                focusedContainerColor = if (isDark) ObsidianSurface.copy(alpha = 0.8f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                unfocusedContainerColor = if (isDark) ObsidianElevated.copy(alpha = 0.6f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.15f),
                disabledContainerColor = if (isDark) ObsidianBase else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.08f)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        if (isError && errorMessage != null) {
            Spacer(Modifier.height(4.dp))
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

/**
 * Pipeline Funnel Bar Widget (Bespoke CRM Widget)
 */
@Composable
fun PipelineStageBar(
    activeCompaniesCount: Int,
    modifier: Modifier = Modifier
) {
    val stages = listOf(
        Triple("Leads", 0.35f, ElectricCyan),
        Triple("Propostas", 0.25f, RoyalSapphire),
        Triple("Negociação", 0.20f, VioletVibrant),
        Triple("Fechado", 0.20f, EmeraldPulse)
    )

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "FUNIL DE RELACIONAMENTO",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Surface(
                shape = RoundedCornerShape(50),
                color = EmeraldPulse.copy(alpha = 0.15f)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(EmeraldPulse)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = "$activeCompaniesCount ativas",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldPulse,
                        fontSize = 11.sp
                    )
                }
            }
        }

        Spacer(Modifier.height(10.dp))

        // Segmented visual bar
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

        Spacer(Modifier.height(8.dp))

        // Stage labels
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
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

/**
 * Mini Activity Sparkline Chart (Compose Canvas Drawn)
 */
@Composable
fun MiniActivityChart(
    modifier: Modifier = Modifier
) {
    val barValues = listOf(0.4f, 0.7f, 0.5f, 0.9f, 0.6f, 0.85f, 1.0f)
    val days = listOf("Seg", "Ter", "Qua", "Qui", "Sex", "Sab", "Dom")

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ATIVIDADE SEMANAL",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "+24% vs mês anterior",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = EmeraldPulse
            )
        }

        Spacer(Modifier.height(12.dp))

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
                                if (index == barValues.lastIndex) AndreaElectricGradient
                                else Brush.verticalGradient(
                                    listOf(ElectricCyan.copy(alpha = 0.6f), RoyalSapphire.copy(alpha = 0.3f))
                                )
                            )
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = days[index],
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 9.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

/**
 * Holographic Corporate Pass (Live card shown on Form & Directory)
 */
@Composable
fun CorporatePassHologram(
    companyName: String,
    cnpj: String?,
    businessArea: String?,
    modifier: Modifier = Modifier
) {
    val isDark = isSystemInDarkTheme()
    val bgGradient = if (isDark) AndreaPassGradient else AndreaPassLightGradient

    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(22.dp),
                ambientColor = ElectricCyan.copy(alpha = 0.3f),
                spotColor = RoyalSapphire.copy(alpha = 0.3f)
            ),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(bgGradient)
                .border(
                    width = 1.2.dp,
                    brush = if (isDark) AndreaGlassBorderDark else AndreaGlassBorderLight,
                    shape = RoundedCornerShape(22.dp)
                )
                .padding(20.dp)
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
                                .size(36.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(AndreaElectricGradient),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = companyName.take(1).uppercase().ifEmpty { "A" },
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        Spacer(Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "CORPORATE PASS",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.2.sp,
                                color = ElectricCyan
                            )
                            Text(
                                text = "ID VERIFICADO",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 9.sp,
                                color = EmeraldPulse
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = null,
                        tint = ElectricCyan,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(Modifier.height(18.dp))

                Text(
                    text = companyName.ifEmpty { "Nome da Empresa" },
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = if (isDark) Color.White else TextWhiteHigh.copy(alpha = 0.95f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (!cnpj.isNullOrBlank()) "CNPJ: $cnpj" else "CNPJ: Em aberto",
                        style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace),
                        color = if (isDark) TextWhiteMedium else MaterialTheme.colorScheme.onSurfaceVariant
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
    val isDark = isSystemInDarkTheme()

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
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f, fill = false)
                    )

                    // Active indicator dot
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(EmeraldPulse)
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
                            tint = ElectricCyan.copy(alpha = 0.8f),
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = email,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                            tint = EmeraldPulse.copy(alpha = 0.8f),
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = phone,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                    .background(
                        if (isDark) DarkErrorBg.copy(alpha = 0.5f) else LightErrorBg
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Excluir empresa",
                    tint = RosePulse,
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

    val gradients = listOf(
        listOf(Color(0xFF06B6D4), Color(0xFF6366F1)), // Cyan - Sapphire
        listOf(Color(0xFF6366F1), Color(0xFF8B5CF6)), // Sapphire - Violet
        listOf(Color(0xFF10B981), Color(0xFF06B6D4)), // Emerald - Cyan
        listOf(Color(0xFFF59E0B), Color(0xFFF43F5E)), // Amber - Rose
        listOf(Color(0xFFEC4899), Color(0xFF8B5CF6))  // Magenta - Violet
    )
    val colorIndex = abs(name.hashCode()) % gradients.size
    val gradient = Brush.linearGradient(gradients[colorIndex])

    Box(
        modifier = modifier
            .size(size)
            .shadow(6.dp, RoundedCornerShape(14.dp))
            .clip(RoundedCornerShape(14.dp))
            .background(gradient)
            .border(1.dp, Color.White.copy(alpha = 0.25f), RoundedCornerShape(14.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initial,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            color = Color.White
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
    val isDark = isSystemInDarkTheme()

    val (bgColor, textColor, borderCol) = when (area.lowercase()) {
        "tecnologia", "tech", "ti", "software" -> Triple(
            if (isDark) Color(0xFF0E2A47) else Color(0xFFE0F2FE),
            if (isDark) ElectricCyan else Color(0xFF0284C7),
            ElectricCyan.copy(alpha = 0.4f)
        )
        "financeiro", "fintech", "banco", "investimentos" -> Triple(
            if (isDark) Color(0xFF1E1B4B) else Color(0xFFEEF2FF),
            if (isDark) RoyalSapphire else Color(0xFF4F46E5),
            RoyalSapphire.copy(alpha = 0.4f)
        )
        "saúde", "health", "medicina" -> Triple(
            if (isDark) Color(0xFF064E3B) else Color(0xFFD1FAE5),
            if (isDark) EmeraldGlow else Color(0xFF059669),
            EmeraldGlow.copy(alpha = 0.4f)
        )
        "consultoria", "consulting" -> Triple(
            if (isDark) Color(0xFF2E1065) else Color(0xFFF3E8FF),
            if (isDark) VioletVibrant else Color(0xFF7C3AED),
            VioletVibrant.copy(alpha = 0.4f)
        )
        "comércio", "varejo", "loja" -> Triple(
            if (isDark) Color(0xFF451A03) else Color(0xFFFEF3C7),
            if (isDark) AmberGlow else Color(0xFFB45309),
            AmberGlow.copy(alpha = 0.4f)
        )
        else -> Triple(
            MaterialTheme.colorScheme.surfaceVariant,
            MaterialTheme.colorScheme.onSurfaceVariant,
            MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
        )
    }

    Surface(
        shape = RoundedCornerShape(6.dp),
        color = bgColor,
        border = androidx.compose.foundation.BorderStroke(1.dp, borderCol),
        modifier = modifier
    ) {
        Text(
            text = area,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.5.dp)
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
    val isDark = isSystemInDarkTheme()

    val (bg, fg, icon) = when (type) {
        BannerType.ERROR -> Triple(if (isDark) DarkErrorBg else LightErrorBg, if (isDark) DarkErrorFg else LightErrorFg, Icons.Default.Error)
        BannerType.SUCCESS -> Triple(if (isDark) DarkSuccessBg else LightSuccessBg, if (isDark) DarkSuccessFg else LightSuccessFg, Icons.Default.CheckCircle)
        BannerType.WARNING -> Triple(if (isDark) DarkWarningBg else LightWarningBg, if (isDark) DarkWarningFg else LightWarningFg, Icons.Default.Warning)
        BannerType.INFO -> Triple(if (isDark) Color(0xFF0E2A47) else Color(0xFFE0F2FE), if (isDark) ElectricCyan else Color(0xFF0284C7), Icons.Default.Info)
    }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = bg,
        border = androidx.compose.foundation.BorderStroke(1.dp, fg.copy(alpha = 0.3f)),
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
                fontWeight = FontWeight.Medium,
                color = fg
            )
        }
    }
}

enum class BannerType {
    ERROR, SUCCESS, WARNING, INFO
}
