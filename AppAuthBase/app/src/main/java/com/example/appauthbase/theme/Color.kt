package com.example.appauthbase.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Obsidian & Deep Space Theme (Linear / Stripe Luxury Aesthetic)
val ObsidianBase = Color(0xFF08090C)
val ObsidianElevated = Color(0xFF0E121A)
val ObsidianSurface = Color(0xFF141A24)
val ObsidianSurfaceHighlight = Color(0xFF1C2432)
val ObsidianBorder = Color(0xFF263245)
val ObsidianBorderSubtle = Color(0xFF1E2736)

// Light Minimalist Theme
val PureWhite = Color(0xFFFFFFFF)
val SnowBackground = Color(0xFFF8FAFC)
val SlateSurfaceLight = Color(0xFFFFFFFF)
val SlateSurfaceVariantLight = Color(0xFFF1F5F9)
val SlateBorderLight = Color(0xFFE2E8F0)
val SlateBorderSubtleLight = Color(0xFFEDF2F7)

// Electric & Neon Accent Palette
val ElectricCyan = Color(0xFF06B6D4)
val ElectricCyanLight = Color(0xFF22D3EE)
val ElectricCyanDark = Color(0xFF0891B2)

val RoyalSapphire = Color(0xFF6366F1)
val RoyalSapphireDark = Color(0xFF4F46E5)
val RoyalSapphireLight = Color(0xFF818CF8)

val EmeraldPulse = Color(0xFF10B981)
val EmeraldGlow = Color(0xFF34D399)
val EmeraldDark = Color(0xFF059669)

val AmberGlow = Color(0xFFF59E0B)
val RosePulse = Color(0xFFF43F5E)
val VioletVibrant = Color(0xFF8B5CF6)

// Text Colors
val TextWhiteHigh = Color(0xFFF8FAFC)
val TextWhiteMedium = Color(0xFF94A3B8)
val TextWhiteLow = Color(0xFF64748B)

val TextDarkHigh = Color(0xFF0F172A)
val TextDarkMedium = Color(0xFF475569)
val TextDarkLow = Color(0xFF94A3B8)

// Status & Alert Containers
val DarkSuccessBg = Color(0xFF064E3B)
val DarkSuccessFg = Color(0xFF34D399)
val LightSuccessBg = Color(0xFFD1FAE5)
val LightSuccessFg = Color(0xFF059669)

val DarkErrorBg = Color(0xFF4C0519)
val DarkErrorFg = Color(0xFFFB7185)
val LightErrorBg = Color(0xFFFFE4E6)
val LightErrorFg = Color(0xFFE11D48)

val DarkWarningBg = Color(0xFF451A03)
val DarkWarningFg = Color(0xFFFBBF24)
val LightWarningBg = Color(0xFFFEF3C7)
val LightWarningFg = Color(0xFFD97706)

// Bespoke Gradients
val AndreaElectricGradient = Brush.horizontalGradient(
    listOf(Color(0xFF06B6D4), Color(0xFF6366F1), Color(0xFF8B5CF6))
)

val AndreaPrimaryGradient = Brush.horizontalGradient(
    listOf(Color(0xFF4F46E5), Color(0xFF6366F1), Color(0xFF06B6D4))
)

val AndreaAccentGradient = Brush.linearGradient(
    listOf(Color(0xFF06B6D4), Color(0xFF10B981))
)

val AndreaPassGradient = Brush.linearGradient(
    listOf(Color(0xFF1E1B4B), Color(0xFF0F172A), Color(0xFF134E4A))
)

val AndreaPassLightGradient = Brush.linearGradient(
    listOf(Color(0xFFEEF2FF), Color(0xFFF0FDF4), Color(0xFFE0F2FE))
)

val AndreaGlassBorderDark = Brush.verticalGradient(
    listOf(
        Color.White.copy(alpha = 0.22f),
        Color.White.copy(alpha = 0.05f),
        Color.Transparent
    )
)

val AndreaGlassBorderLight = Brush.verticalGradient(
    listOf(
        Color(0xFF6366F1).copy(alpha = 0.3f),
        Color(0xFFE2E8F0),
        Color(0xFFCBD5E1)
    )
)

val AndreaAmbientGlowDark = Brush.radialGradient(
    colors = listOf(
        Color(0xFF6366F1).copy(alpha = 0.18f),
        Color(0xFF06B6D4).copy(alpha = 0.08f),
        Color.Transparent
    )
)

val AndreaAmbientGlowLight = Brush.radialGradient(
    colors = listOf(
        Color(0xFF6366F1).copy(alpha = 0.10f),
        Color(0xFF06B6D4).copy(alpha = 0.05f),
        Color.Transparent
    )
)
