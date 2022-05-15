package io.github.warahiko.mus.ui.util

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class PreviewThemes

@Preview(
    showBackground = true,
    widthDp = 300,
)
@Preview(
    showBackground = true,
    widthDp = 500,
)
annotation class PreviewWidths
