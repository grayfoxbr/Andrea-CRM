package com.example.appauthbase.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = ElectricCyan,
    onPrimary = ObsidianBase,
    primaryContainer = ObsidianSurfaceHighlight,
    onPrimaryContainer = ElectricCyanLight,

    secondary = RoyalSapphireLight,
    onSecondary = ObsidianBase,
    secondaryContainer = ObsidianSurface,
    onSecondaryContainer = RoyalSapphireLight,

    tertiary = EmeraldGlow,
    onTertiary = ObsidianBase,
    tertiaryContainer = DarkSuccessBg,
    onTertiaryContainer = EmeraldGlow,

    background = ObsidianBase,
    onBackground = TextWhiteHigh,

    surface = ObsidianElevated,
    onSurface = TextWhiteHigh,
    surfaceVariant = ObsidianSurface,
    onSurfaceVariant = TextWhiteMedium,

    error = RosePulse,
    onError = TextWhiteHigh,
    errorContainer = DarkErrorBg,
    onErrorContainer = DarkErrorFg,

    outline = ObsidianBorder,
    outlineVariant = ObsidianBorderSubtle
)

private val LightColorScheme = lightColorScheme(
    primary = RoyalSapphireDark,
    onPrimary = PureWhite,
    primaryContainer = SlateSurfaceVariantLight,
    onPrimaryContainer = RoyalSapphireDark,

    secondary = ElectricCyanDark,
    onSecondary = PureWhite,
    secondaryContainer = SlateSurfaceVariantLight,
    onSecondaryContainer = ElectricCyanDark,

    tertiary = EmeraldDark,
    onTertiary = PureWhite,
    tertiaryContainer = LightSuccessBg,
    onTertiaryContainer = LightSuccessFg,

    background = SnowBackground,
    onBackground = TextDarkHigh,

    surface = SlateSurfaceLight,
    onSurface = TextDarkHigh,
    surfaceVariant = SlateSurfaceVariantLight,
    onSurfaceVariant = TextDarkMedium,

    error = RosePulse,
    onError = PureWhite,
    errorContainer = LightErrorBg,
    onErrorContainer = LightErrorFg,

    outline = SlateBorderLight,
    outlineVariant = SlateBorderSubtleLight
)

@Composable
fun AppAuthBaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Preserve brand identity
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                val insetsController = WindowCompat.getInsetsController(window, view)
                insetsController.isAppearanceLightStatusBars = !darkTheme
                insetsController.isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
