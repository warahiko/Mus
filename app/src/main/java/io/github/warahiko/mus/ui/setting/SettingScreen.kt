package io.github.warahiko.mus.ui.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.warahiko.mus.ui.component.MusMainTopBar
import io.github.warahiko.mus.ui.theme.MusAppTheme

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MusMainTopBar()
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
        ) {
            Text(
                text = "Setting",
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    MusAppTheme {
        SettingScreen()
    }
}
