package com.example.pruebascompose.tv.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme

@OptIn(ExperimentalTvMaterial3Api::class)
private val TvDarkColorScheme = darkColorScheme(
    primary = Color(0xFF5B3FE4),
    onPrimary = Color.White,
    secondary = Color(0xFFF5A524),
    onSecondary = Color(0xFF0E0B14),
    background = Color(0xFF0E0B14),
    onBackground = Color.White,
    surface = Color(0xFF1A1625),
    onSurface = Color.White,
    error = Color(0xFFCF6679),
    onError = Color.White,
)

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TvTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = TvDarkColorScheme,
        content = content
    )
}
