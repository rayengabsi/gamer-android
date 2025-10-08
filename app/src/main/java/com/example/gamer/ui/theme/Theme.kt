package com.example.gamer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Light Mode Colors
private val LightPrimary = Color(0xFFE91E63)
private val LightBackground = Color(0xFFFFFFFF)
private val LightSurface = Color(0xFFFFFFFF)
private val LightOnBackground = Color(0xFF000000)
private val LightOnSurface = Color(0xFF000000)

// Dark Mode Colors
private val DarkPrimary = Color(0xFFE91E63)
private val DarkBackground = Color(0xFF121212)
private val DarkSurface = Color(0xFF1E1E1E)
private val DarkOnBackground = Color(0xFFFFFFFF)
private val DarkOnSurface = Color(0xFFFFFFFF)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    background = LightBackground,
    surface = LightSurface,
    onBackground = LightOnBackground,
    onSurface = LightOnSurface,
    error = Color(0xFFB00020)
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    background = DarkBackground,
    surface = DarkSurface,
    onBackground = DarkOnBackground,
    onSurface = DarkOnSurface,
    error = Color(0xFFCF6679)
)

@Composable
fun GamerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}