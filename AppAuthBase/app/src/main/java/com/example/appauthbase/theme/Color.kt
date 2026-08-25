package com.example.appauthbase.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Primary Brand Colors (Indigo & Royal Violet)
val AndreaPrimary = Color(0xFF6366F1)
val AndreaPrimaryDark = Color(0xFF4F46E5)
val AndreaPrimaryLight = Color(0xFF818CF8)
val AndreaPrimaryContainerLight = Color(0xFFEEF2FF)
val AndreaPrimaryContainerDark = Color(0xFF1E1B4B)

// Secondary Colors (Teal & Cyan)
val AndreaSecondary = Color(0xFF0EA5E9)
val AndreaSecondaryDark = Color(0xFF0284C7)
val AndreaSecondaryLight = Color(0xFF38BDF8)
val AndreaSecondaryContainerLight = Color(0xFFE0F2FE)
val AndreaSecondaryContainerDark = Color(0xFF082F49)

// Tertiary Accent (Violet / Magenta)
val AndreaTertiary = Color(0xFF8B5CF6)
val AndreaTertiaryDark = Color(0xFF7C3AED)
val AndreaTertiaryLight = Color(0xFFA78BFA)
val AndreaTertiaryContainerLight = Color(0xFFF3E8FF)
val AndreaTertiaryContainerDark = Color(0xFF2E1065)

// Neutral & Backgrounds - Dark Theme
val AndreaDarkBackground = Color(0xFF0B0F19)
val AndreaDarkSurface = Color(0xFF111827)
val AndreaDarkSurfaceVariant = Color(0xFF1F2937)
val AndreaDarkCard = Color(0xFF172033)
val AndreaDarkCardBorder = Color(0xFF25334D)
val AndreaDarkOnBackground = Color(0xFFF8FAFC)
val AndreaDarkOnSurface = Color(0xFFF1F5F9)
val AndreaDarkOnSurfaceVariant = Color(0xFF94A3B8)

// Neutral & Backgrounds - Light Theme
val AndreaLightBackground = Color(0xFFF8FAFC)
val AndreaLightSurface = Color(0xFFFFFFFF)
val AndreaLightSurfaceVariant = Color(0xFFF1F5F9)
val AndreaLightCard = Color(0xFFFFFFFF)
val AndreaLightCardBorder = Color(0xFFE2E8F0)
val AndreaLightOnBackground = Color(0xFF0F172A)
val AndreaLightOnSurface = Color(0xFF1E293B)
val AndreaLightOnSurfaceVariant = Color(0xFF64748B)

// Status & Semantic Colors
val AndreaSuccess = Color(0xFF10B981)
val AndreaSuccessContainer = Color(0xFFD1FAE5)
val AndreaSuccessDark = Color(0xFF065F46)

val AndreaWarning = Color(0xFFF59E0B)
val AndreaWarningContainer = Color(0xFFFEF3C7)

val AndreaError = Color(0xFFEF4444)
val AndreaErrorContainer = Color(0xFFFEE2E2)
val AndreaErrorDarkContainer = Color(0xFF450A0A)

val AndreaInfo = Color(0xFF3B82F6)
val AndreaInfoContainer = Color(0xFFDBEAFE)

// Gradient Brushes
val AndreaPrimaryGradient = Brush.horizontalGradient(
    listOf(Color(0xFF6366F1), Color(0xFF8B5CF6))
)

val AndreaAccentGradient = Brush.horizontalGradient(
    listOf(Color(0xFF0EA5E9), Color(0xFF6366F1))
)

val AndreaCardGradientDark = Brush.linearGradient(
    listOf(Color(0xFF1E293B), Color(0xFF111827))
)

val AndreaCardGradientLight = Brush.linearGradient(
    listOf(Color(0xFFFFFFFF), Color(0xFFF8FAFC))
)

val AndreaHeroGradientDark = Brush.verticalGradient(
    listOf(Color(0xFF1E1B4B), Color(0xFF0B0F19))
)

val AndreaHeroGradientLight = Brush.verticalGradient(
    listOf(Color(0xFFEEF2FF), Color(0xFFF8FAFC))
)
