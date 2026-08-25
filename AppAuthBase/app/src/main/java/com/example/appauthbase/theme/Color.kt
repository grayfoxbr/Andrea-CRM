package com.example.appauthbase.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Pure Luxury Black & White / Obsidian Palette
val JetBlack = Color(0xFF030406)
val PitchDark = Color(0xFF080B10)
val DarkGlass = Color(0xFF0E131C)
val DarkGlassElevated = Color(0xFF151C28)
val DarkGlassHighlight = Color(0xFF1F293B)

val PureWhite = Color(0xFFFFFFFF)
val PearlWhite = Color(0xFFF8FAFC)
val SilverMuted = Color(0xFF94A3B8)
val DarkMuted = Color(0xFF64748B)

// Moving Aurora Colors (Cyan, Violet, Emerald, Rose)
val AuroraCyan = Color(0xFF00E5FF)
val AuroraViolet = Color(0xFF8B5CF6)
val AuroraIndigo = Color(0xFF6366F1)
val AuroraEmerald = Color(0xFF10B981)
val AuroraRose = Color(0xFFF43F5E)
val AuroraAmber = Color(0xFFF59E0B)

// Text Colors
val TextPureWhite = Color(0xFFFFFFFF)
val TextPearl = Color(0xFFF1F5F9)
val TextSilver = Color(0xFF94A3B8)
val TextMuted = Color(0xFF64748B)

val TextJetBlack = Color(0xFF030406)
val TextDarkGray = Color(0xFF1E293B)

// Status & Alert Containers
val DarkSuccessBg = Color(0xFF052E16)
val DarkSuccessFg = Color(0xFF34D399)
val DarkErrorBg = Color(0xFF3F0713)
val DarkErrorFg = Color(0xFFFB7185)
val DarkWarningBg = Color(0xFF361803)
val DarkWarningFg = Color(0xFFFBBF24)

// Monochrome & High-Contrast Gradients
val WhiteToSilverGradient = Brush.horizontalGradient(
    listOf(Color(0xFFFFFFFF), Color(0xFFE2E8F0), Color(0xFFCBD5E1))
)

val BlackToObsidianGradient = Brush.verticalGradient(
    listOf(Color(0xFF151C28), Color(0xFF080B10))
)

val AuroraShineGradient = Brush.horizontalGradient(
    listOf(Color(0xFF00E5FF), Color(0xFF8B5CF6), Color(0xFF10B981))
)

val SpecularBorderWhite = Brush.verticalGradient(
    listOf(
        Color.White.copy(alpha = 0.35f),
        Color.White.copy(alpha = 0.08f),
        Color.Transparent
    )
)

val SpecularBorderSubtle = Brush.verticalGradient(
    listOf(
        Color.White.copy(alpha = 0.20f),
        Color.White.copy(alpha = 0.04f),
        Color.Transparent
    )
)

val CorporatePassMonochrome = Brush.linearGradient(
    listOf(
        Color(0xFF18202F),
        Color(0xFF0A0E17),
        Color(0xFF101724)
    )
)
