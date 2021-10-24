package com.example.stattrack.View.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val defaultColors = lightColors(
    primary = PrimaryBlue,
    background = PrimaryWhite
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun StattrackTheme(content: @Composable() () -> Unit) {

    MaterialTheme(
        colors = defaultColors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}