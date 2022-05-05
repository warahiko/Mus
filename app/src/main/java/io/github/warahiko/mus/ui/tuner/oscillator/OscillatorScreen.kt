package io.github.warahiko.mus.ui.tuner.oscillator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.warahiko.mus.R
import io.github.warahiko.mus.ui.component.MusTopBar
import io.github.warahiko.mus.ui.theme.MusAppTheme

@Composable
fun OscillatorScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MusTopBar(
                title = stringResource(id = R.string.oscillator_title),
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
        ) {
            Text(
                text = "Oscillator",
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Preview
@Composable
private fun OscillatorScreenPreview() {
    MusAppTheme {
        OscillatorScreen()
    }
}
