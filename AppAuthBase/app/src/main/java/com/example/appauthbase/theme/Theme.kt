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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = NeoYellow,
    onPrimary = NeoBlack,
    primaryContainer = NeoLime,
    onPrimaryContainer = NeoBlack,

    secondary = NeoCyan,
    onSecondary = NeoBlack,
    secondaryContainer = NeoPurple,
    onSecondaryContainer = NeoBlack,

    tertiary = NeoPink,
    onTertiary = NeoBlack,
    tertiaryContainer = NeoOrange,
    onTertiaryContainer = NeoBlack,

    background = NeoBackground,
    onBackground = NeoTextDark,

    surface = NeoCardWhite,
    onSurface = NeoTextDark,
    surfaceVariant = Color(0xFFF4F4F5),
    onSurfaceVariant = NeoTextMuted,

    error = NeoPink,
    onError = Color.White,
    errorContainer = NeoErrorBg,
    onErrorContainer = NeoBlack,

    outline = NeoBlack,
    outlineVariant = NeoBorderGray
)

private val DarkColorScheme = darkColorScheme(
    primary = NeoYellow,
    onPrimary = NeoBlack,
    primaryContainer = NeoLime,
    onPrimaryContainer = NeoBlack,

    secondary = NeoCyan,
    onSecondary = NeoBlack,
    secondaryContainer = NeoPurple,
    onSecondaryContainer = NeoBlack,

    tertiary = NeoPink,
    onTertiary = NeoBlack,
    tertiaryContainer = NeoOrange,
    onTertiaryContainer = NeoBlack,

    background = NeoBackgroundDark,
    onBackground = Color.White,

    surface = Color(0xFF27272A),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF3F3F46),
    onSurfaceVariant = Color(0xFFA1A1AA),

    error = NeoPink,
    onError = Color.White,
    errorContainer = Color(0xFF881337),
    onErrorContainer = Color.White,

    outline = Color.White,
    outlineVariant = Color(0xFF52525B)
)

@Composable
fun AppAuthBaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

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
