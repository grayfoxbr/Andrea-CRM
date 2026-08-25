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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = AndreaPrimary,
    onPrimary = AndreaDarkOnBackground,
    primaryContainer = AndreaPrimaryContainerDark,
    onPrimaryContainer = AndreaPrimaryLight,

    secondary = AndreaSecondary,
    onSecondary = AndreaDarkOnBackground,
    secondaryContainer = AndreaSecondaryContainerDark,
    onSecondaryContainer = AndreaSecondaryLight,

    tertiary = AndreaTertiary,
    onTertiary = AndreaDarkOnBackground,
    tertiaryContainer = AndreaTertiaryContainerDark,
    onTertiaryContainer = AndreaTertiaryLight,

    background = AndreaDarkBackground,
    onBackground = AndreaDarkOnBackground,

    surface = AndreaDarkSurface,
    onSurface = AndreaDarkOnSurface,
    surfaceVariant = AndreaDarkSurfaceVariant,
    onSurfaceVariant = AndreaDarkOnSurfaceVariant,

    error = AndreaError,
    onError = AndreaDarkOnBackground,
    errorContainer = AndreaErrorDarkContainer,
    onErrorContainer = AndreaError,

    outline = AndreaDarkCardBorder,
    outlineVariant = AndreaDarkSurfaceVariant
)

private val LightColorScheme = lightColorScheme(
    primary = AndreaPrimaryDark,
    onPrimary = AndreaLightSurface,
    primaryContainer = AndreaPrimaryContainerLight,
    onPrimaryContainer = AndreaPrimaryDark,

    secondary = AndreaSecondaryDark,
    onSecondary = AndreaLightSurface,
    secondaryContainer = AndreaSecondaryContainerLight,
    onSecondaryContainer = AndreaSecondaryDark,

    tertiary = AndreaTertiaryDark,
    onTertiary = AndreaLightSurface,
    tertiaryContainer = AndreaTertiaryContainerLight,
    onTertiaryContainer = AndreaTertiaryDark,

    background = AndreaLightBackground,
    onBackground = AndreaLightOnBackground,

    surface = AndreaLightSurface,
    onSurface = AndreaLightOnSurface,
    surfaceVariant = AndreaLightSurfaceVariant,
    onSurfaceVariant = AndreaLightOnSurfaceVariant,

    error = AndreaError,
    onError = AndreaLightSurface,
    errorContainer = AndreaErrorContainer,
    onErrorContainer = AndreaError,

    outline = AndreaLightCardBorder,
    outlineVariant = AndreaLightSurfaceVariant
)

@Composable
fun AppAuthBaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep consistent brand identity by default
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
