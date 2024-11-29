package com.cipta.colorwise.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Composable
fun ColorWiseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Automatically use system dark mode
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    ProvideColorWiseColors(colors) {
        MaterialTheme(
            colorScheme = colors.toColorScheme(), // Convert ColorWiseColors to ColorScheme
            typography = Typography, // You can define your custom typography here
            shapes = Shapes, // You can define your custom shapes here
            content = content
        )
    }
}

object ColorWiseTheme {
    val colors: ColorWiseColors
        @Composable
        get() = LocalColorWiseColors.current
}

/**
 * ColorWise custom Color Palette
 */
@Immutable
data class ColorWiseColors(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val error: Color,
    val onError: Color,
    val isDark: Boolean,
    val gradient6_1: List<Color>,
)

/**
 * Extension function to convert ColorWiseColors to ColorScheme
 */
fun ColorWiseColors.toColorScheme(): ColorScheme {
    return ColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primary,
        onPrimaryContainer = onPrimary,
        inversePrimary = primary,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondary,
        onSecondaryContainer = onSecondary,
        tertiary = secondary, // You can define this color if you want
        onTertiary = onSecondary, // Adjust accordingly
        tertiaryContainer = secondary, // Adjust accordingly
        onTertiaryContainer = onSecondary, // Adjust accordingly
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surface, // Adjust accordingly
        onSurfaceVariant = onSurface, // Adjust accordingly
        surfaceTint = surface,
        inverseSurface = surface,
        inverseOnSurface = onSurface,
        error = error,
        onError = onError,
        errorContainer = error,
        onErrorContainer = onError,
        outline = onSurface, // Adjust accordingly
        outlineVariant = onSurface, // Adjust accordingly
        scrim = Color.Black, // Adjust accordingly
        surfaceBright = surface,
        surfaceDim = surface,
        surfaceContainer = surface,
        surfaceContainerHigh = surface,
        surfaceContainerHighest = surface,
        surfaceContainerLow = surface,
        surfaceContainerLowest = surface
    )
}

@Composable
fun ProvideColorWiseColors(
    colors: ColorWiseColors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalColorWiseColors provides colors, content = content)
}

private val LocalColorWiseColors = staticCompositionLocalOf<ColorWiseColors> {
    error("No ColorWiseColorPalette provided")
}

/**
 * Light and Dark color palettes for ColorWise theme
 */
val LightColors = ColorWiseColors(
    primary = Color(0xFF6200EE),
    onPrimary = Color.White,
    secondary = Color(0xFF03DAC6),
    onSecondary = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color(0xFFFBFBFB),
    onSurface = Color.Black,
    error = Color(0xFFB00020),
    onError = Color.White,
    isDark = false,
    gradient6_1 = listOf(LightPurple, MediumPurple),

)

val DarkColors = ColorWiseColors(
    primary = Color(0xFFBB86FC),
    onPrimary = Color.Black,
    secondary = Color(0xFF03DAC6),
    onSecondary = Color.Black,
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF121212),
    onSurface = Color.White,
    error = Color(0xFFCF6679),
    onError = Color.Black,
    isDark = true,
    gradient6_1 = listOf(LightPurple, MediumPurple),
)

@Composable
fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Magenta
) = ColorScheme(
    primary = debugColor,
    onPrimary = debugColor,
    primaryContainer = debugColor,
    onPrimaryContainer = debugColor,
    inversePrimary = debugColor,
    secondary = debugColor,
    onSecondary = debugColor,
    secondaryContainer = debugColor,
    onSecondaryContainer = debugColor,
    tertiary = debugColor,
    onTertiary = debugColor,
    tertiaryContainer = debugColor,
    onTertiaryContainer = debugColor,
    background = debugColor,
    onBackground = debugColor,
    surface = debugColor,
    onSurface = debugColor,
    surfaceVariant = debugColor,
    onSurfaceVariant = debugColor,
    surfaceTint = debugColor,
    inverseSurface = debugColor,
    inverseOnSurface = debugColor,
    error = debugColor,
    onError = debugColor,
    errorContainer = debugColor,
    onErrorContainer = debugColor,
    outline = debugColor,
    outlineVariant = debugColor,
    scrim = debugColor,
    surfaceBright = debugColor,
    surfaceDim = debugColor,
    surfaceContainer = debugColor,
    surfaceContainerHigh = debugColor,
    surfaceContainerHighest = debugColor,
    surfaceContainerLow = debugColor,
    surfaceContainerLowest = debugColor,
)
