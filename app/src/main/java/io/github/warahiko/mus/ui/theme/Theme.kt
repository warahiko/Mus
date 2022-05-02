package io.github.warahiko.mus.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme()

private val DarkColorScheme = darkColorScheme()

@Composable
fun MusAppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isDynamic: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (isDynamic) {
        val context = LocalContext.current
        if (isDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (isDarkTheme) DarkColorScheme else LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}
