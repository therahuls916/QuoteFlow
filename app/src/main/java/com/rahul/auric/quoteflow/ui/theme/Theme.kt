// Theme.kt
package com.rahul.auric.quoteflow.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = AccentTeal,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = BackgroundDark, // Text on top of AccentTeal buttons
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun QuoteFlowTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography, // ✅ USE THE RENAMED VARIABLE HERE
        shapes = Shapes,
        content = content
    )
}