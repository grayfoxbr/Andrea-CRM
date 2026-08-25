package com.example.appauthbase.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appauthbase.data.remote.dto.CompanyDto
import com.example.appauthbase.theme.NeoBackground
import com.example.appauthbase.theme.NeoBlack
import com.example.appauthbase.theme.NeoCardWhite
import com.example.appauthbase.theme.NeoCyan
import com.example.appauthbase.theme.NeoEmerald
import com.example.appauthbase.theme.NeoErrorBg
import com.example.appauthbase.theme.NeoErrorBorder
import com.example.appauthbase.theme.NeoLime
import com.example.appauthbase.theme.NeoOrange
import com.example.appauthbase.theme.NeoPink
import com.example.appauthbase.theme.NeoPurple
import com.example.appauthbase.theme.NeoSuccessBg
import com.example.appauthbase.theme.NeoSuccessBorder
import com.example.appauthbase.theme.NeoTextDark
import com.example.appauthbase.theme.NeoTextMuted
import com.example.appauthbase.theme.NeoWarningBg
import com.example.appauthbase.theme.NeoWarningBorder
import com.example.appauthbase.theme.NeoYellow
import kotlin.math.abs

/**
 * Modifier for Solid Hard Offset Shadows (Neo-Brutalist Signature Effect)
 */
fun Modifier.neoShadow(
    offsetX: Dp = 4.dp,
    offsetY: Dp = 4.dp,
    color: Color = NeoBlack,
    cornerRadius: Dp = 18.dp
): Modifier = this.drawBehind {
    val cornerPx = cornerRadius.toPx()
    val offXPx = offsetX.toPx()
    val offYPx = offsetY.toPx()

    // Draw solid shadow rectangle with rounded corners
    drawRoundRect(
        color = color,
        topLeft = Offset(offXPx, offYPx),
        size = Size(size.width, size.height),
        cornerRadius = CornerRadius(cornerPx, cornerPx)
    )
}

/**
 * Neo-Brutalist Base Container Card
 */
@Composable
fun LuxuryGlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 18.dp,
    backgroundColor: Color = NeoCardWhite,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .neoShadow(offsetX = 4.dp, offsetY = 4.dp, cornerRadius = cornerRadius)
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor)
            .border(
                width = 2.5.dp,
                color = NeoBlack,
                shape = RoundedCornerShape(cornerRadius)
            )
    ) {
        content()
    }
}

/**
 * Physical Folder Tab Bento Card (Matching Exact Reference Image)
 */
@Composable
fun NeoFolderCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    tabColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isNew: Boolean = false
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
    ) {
        // Colored Top Folder Tab
        Row(
            modifier = Modifier.padding(start = 14.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(72.dp)
                    .height(14.dp)
                    .neoShadow(offsetX = 3.dp, offsetY = 0.dp, cornerRadius = 6.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .background(tabColor)
                    .border(
                        width = 2.5.dp,
                        color = NeoBlack,
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    )
            )
        }

        // Folder Main Body Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .neoShadow(offsetX = 4.dp, offsetY = 4.dp, cornerRadius = 18.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(NeoCardWhite)
                .border(2.5.dp, NeoBlack, RoundedCornerShape(18.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Icon Box
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(tabColor)
                            .border(2.dp, NeoBlack, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = NeoBlack,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    // Count / Subtitle
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = NeoTextMuted
                        )
                        if (isNew) {
                            Spacer(Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = NeoPink,
                                border = androidx.compose.foundation.BorderStroke(1.5.dp, NeoBlack)
                            ) {
                                Text(
                                    text = "NEW",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(18.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Black,
                    color = NeoBlack,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

/**
 * Neo-Brutalist Brand Monogram Logo
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
        Box(
            modifier = Modifier
                .size(if (isLarge) 68.dp else 50.dp)
                .neoShadow(offsetX = 4.dp, offsetY = 4.dp, cornerRadius = 18.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(NeoYellow)
                .border(2.5.dp, NeoBlack, RoundedCornerShape(18.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Domain,
                contentDescription = "Andrea CRM Logo",
                tint = NeoBlack,
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
                color = NeoBlack
            )
            Spacer(Modifier.width(8.dp))
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = NeoBlack,
                border = androidx.compose.foundation.BorderStroke(2.dp, NeoBlack)
            ) {
                Text(
                    text = "CRM",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.5.sp,
                    color = NeoYellow,
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                )
            }
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = "Save now. Find anytime. Scale effortlessly.",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = NeoTextMuted
        )
    }
}

/**
 * Neo-Brutalist Primary Action Button (Yellow Pop with 2.5dp Black Border & Solid Shadow)
 */
@Composable
fun AndreaPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    icon: ImageVector? = null,
    backgroundColor: Color = NeoYellow,
    height: Dp = 54.dp
) {
    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .neoShadow(offsetX = 4.dp, offsetY = 4.dp, cornerRadius = 14.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = NeoBlack,
            disabledContainerColor = Color(0xFFE4E4E7),
            disabledContentColor = Color(0xFFA1A1AA)
        ),
        border = androidx.compose.foundation.BorderStroke(2.5.dp, NeoBlack),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = NeoBlack,
                strokeWidth = 3.dp
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
                        tint = NeoBlack,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.2.sp,
                    color = NeoBlack
                )
            }
        }
    }
}

/**
 * Neo-Brutalist Square Icon Button (Header action buttons)
 */
@Composable
fun NeoIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = NeoCardWhite,
    size: Dp = 46.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .neoShadow(offsetX = 3.dp, offsetY = 3.dp, cornerRadius = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .border(2.5.dp, NeoBlack, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = NeoBlack,
            modifier = Modifier.size(22.dp)
        )
    }
}

/**
 * Neo-Brutalist Outlined Secondary Button
 */
@Composable
fun AndreaOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    height: Dp = 50.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .neoShadow(offsetX = 3.dp, offsetY = 3.dp, cornerRadius = 14.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(NeoCardWhite)
            .border(2.5.dp, NeoBlack, RoundedCornerShape(14.dp))
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = NeoBlack,
                    modifier = Modifier.size(19.dp)
                )
                Spacer(Modifier.width(8.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Black,
                color = NeoBlack
            )
        }
    }
}

/**
 * Neo-Brutalist Input Field with 2.5dp Black Border and Solid Shadow
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .neoShadow(offsetX = 3.dp, offsetY = 3.dp, cornerRadius = 14.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(NeoCardWhite)
                .border(2.5.dp, if (isError) NeoErrorBorder else NeoBlack, RoundedCornerShape(14.dp))
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                label = { Text(label, fontWeight = FontWeight.Black, color = NeoBlack) },
                placeholder = placeholder?.let { { Text(it, color = NeoTextMuted) } },
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
                            tint = NeoBlack,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                trailingIcon = {
                    if (isPassword) {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = if (passwordVisible) "Ocultar senha" else "Mostrar senha",
                                tint = NeoBlack
                            )
                        }
                    } else if (value.isNotEmpty() && enabled) {
                        IconButton(onClick = { onValueChange("") }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Limpar texto",
                                tint = NeoBlack,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = NeoBlack,
                    unfocusedTextColor = NeoBlack,
                    focusedLabelColor = NeoBlack,
                    unfocusedLabelColor = NeoTextMuted
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (isError && errorMessage != null) {
            Spacer(Modifier.height(4.dp))
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = NeoErrorBorder,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

/**
 * Company Card for Directory (Folder Tab Styling)
 */
@Composable
fun CompanyCorporateCard(
    company: CompanyDto,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tabColors = listOf(NeoLime, NeoYellow, NeoPink, NeoCyan, NeoPurple, NeoOrange)
    val colorIndex = abs((company.companyName.hashCode())) % tabColors.size
    val tabColor = tabColors[colorIndex]

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        // Folder Tab
        Row(modifier = Modifier.padding(start = 16.dp)) {
            Box(
                modifier = Modifier
                    .width(76.dp)
                    .height(13.dp)
                    .neoShadow(offsetX = 3.dp, offsetY = 0.dp, cornerRadius = 6.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .background(tabColor)
                    .border(2.5.dp, NeoBlack, RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            )
        }

        // Card Body
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .neoShadow(offsetX = 4.dp, offsetY = 4.dp, cornerRadius = 18.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(NeoCardWhite)
                .border(2.5.dp, NeoBlack, RoundedCornerShape(18.dp))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                // Initial Box
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(tabColor)
                        .border(2.dp, NeoBlack, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = company.companyName.take(1).uppercase().ifEmpty { "A" },
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Black,
                        color = NeoBlack
                    )
                }

                Spacer(Modifier.width(14.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 6.dp)
                ) {
                    Text(
                        text = company.companyName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Black,
                        color = NeoBlack,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (!company.businessArea.isNullOrBlank()) {
                        Spacer(Modifier.height(4.dp))
                        CategoryBadge(area = company.businessArea)
                    }

                    company.companyEmail?.takeIf { it.isNotBlank() }?.let { email ->
                        Spacer(Modifier.height(6.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = null,
                                tint = NeoBlack,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(Modifier.width(5.dp))
                            Text(
                                text = email,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = NeoTextMuted,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    company.companyPhoneNumber?.takeIf { it.isNotBlank() }?.let { phone ->
                        Spacer(Modifier.height(2.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = null,
                                tint = NeoBlack,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(Modifier.width(5.dp))
                            Text(
                                text = phone,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = NeoTextMuted,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }

                // Delete Button Box
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(NeoPink)
                        .border(2.dp, NeoBlack, RoundedCornerShape(10.dp))
                        .clickable(onClick = onDeleteClick),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Excluir empresa",
                        tint = NeoBlack,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

/**
 * Holographic / Neo-Brutalist Live Preview Pass
 */
@Composable
fun CorporatePassHologram(
    companyName: String,
    cnpj: String?,
    businessArea: String?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(start = 18.dp)) {
            Box(
                modifier = Modifier
                    .width(88.dp)
                    .height(15.dp)
                    .neoShadow(offsetX = 3.dp, offsetY = 0.dp, cornerRadius = 6.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .background(NeoYellow)
                    .border(2.5.dp, NeoBlack, RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .neoShadow(offsetX = 5.dp, offsetY = 5.dp, cornerRadius = 20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(NeoCardWhite)
                .border(2.5.dp, NeoBlack, RoundedCornerShape(20.dp))
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
                                .size(42.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(NeoLime)
                                .border(2.dp, NeoBlack, RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = companyName.take(1).uppercase().ifEmpty { "A" },
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Black,
                                color = NeoBlack
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "CORPORATE PASS",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.5.sp,
                                color = NeoBlack
                            )
                            Text(
                                text = "● VERIFICADO",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = NeoEmerald
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = null,
                        tint = NeoBlack,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(Modifier.height(18.dp))

                Text(
                    text = companyName.ifEmpty { "Razão Social da Empresa" },
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Black,
                    color = NeoBlack,
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
                        text = if (!cnpj.isNullOrBlank()) "CNPJ: $cnpj" else "CNPJ: Não informado",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = NeoTextMuted
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
 * Category Badge with Neo-Brutalist Border and Pastel Colors
 */
@Composable
fun CategoryBadge(
    area: String,
    modifier: Modifier = Modifier
) {
    val (bgColor, textColor) = when (area.lowercase()) {
        "tecnologia", "tech", "ti", "software" -> Pair(NeoCyan, NeoBlack)
        "financeiro", "fintech", "banco", "investimentos" -> Pair(NeoPurple, NeoBlack)
        "saúde", "health", "medicina" -> Pair(NeoEmerald, NeoBlack)
        "consultoria", "consulting" -> Pair(NeoOrange, NeoBlack)
        "comércio", "varejo", "loja" -> Pair(NeoPink, NeoBlack)
        else -> Pair(NeoYellow, NeoBlack)
    }

    Surface(
        shape = RoundedCornerShape(8.dp),
        color = bgColor,
        border = androidx.compose.foundation.BorderStroke(2.dp, NeoBlack),
        modifier = modifier
    ) {
        Text(
            text = area,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Black,
            color = textColor,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
    }
}

/**
 * Neo-Brutalist Status Alert Banner
 */
@Composable
fun AndreaStatusBanner(
    message: String,
    type: BannerType = BannerType.ERROR,
    modifier: Modifier = Modifier
) {
    val (bg, icon) = when (type) {
        BannerType.ERROR -> Pair(NeoErrorBg, Icons.Default.Error)
        BannerType.SUCCESS -> Pair(NeoSuccessBg, Icons.Default.Check)
        BannerType.WARNING -> Pair(NeoWarningBg, Icons.Default.Warning)
        BannerType.INFO -> Pair(NeoCardWhite, Icons.Default.Info)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .neoShadow(offsetX = 3.dp, offsetY = 3.dp, cornerRadius = 14.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(bg)
            .border(2.5.dp, NeoBlack, RoundedCornerShape(14.dp))
            .padding(14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = NeoBlack,
                modifier = Modifier.size(22.dp)
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Black,
                color = NeoBlack
            )
        }
    }
}

enum class BannerType {
    ERROR, SUCCESS, WARNING, INFO
}
