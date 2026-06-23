package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = lightColorScheme(
    primary = PretibimbPrimary,
    secondary = InfoBlue,
    tertiary = SuccessGreen,
    background = LightAppBackground,
    surface = LightCardSurface,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = SlateTextMain,
    onSurface = SlateTextMain,
    error = DangerRed
)

private val LightColorScheme = lightColorScheme(
    primary = PretibimbAdminPrimary,
    secondary = InfoBlue,
    tertiary = SuccessGreen,
    background = LightAppBackground,
    surface = LightCardSurface,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = SlateTextMain,
    onSurface = SlateTextMain,
    error = DangerRed
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
