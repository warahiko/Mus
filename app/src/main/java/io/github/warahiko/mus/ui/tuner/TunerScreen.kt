package io.github.warahiko.mus.ui.tuner

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.warahiko.mus.ui.component.MusMainTopBar
import io.github.warahiko.mus.ui.theme.MusAppTheme

@Composable
fun TunerScreen(
    modifier: Modifier = Modifier,
    onClickGoToOscillator: () -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MusMainTopBar()
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Tuner",
            )
            Button(onClick = onClickGoToOscillator) {
                Text(text = "Go to Oscillator")
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TunerScreenPreview() {
    MusAppTheme {
        TunerScreen()
    }
}
