package com.surodeev.currenciestestapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Primary,
    primaryVariant = PrimaryLight,
    secondary = Secondary
)

private val LightColorPalette = lightColors(
    primary = Primary,
    primaryVariant = PrimaryLight,
    secondary = Secondary
)

@Composable
fun MainTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    if (!darkTheme) {
        systemUiController.setStatusBarColor(Primary)
    } else {
        systemUiController.setStatusBarColor(Color(0xFF121212))
    }

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}