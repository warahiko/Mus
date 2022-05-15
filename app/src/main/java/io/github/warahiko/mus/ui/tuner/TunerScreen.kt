package io.github.warahiko.mus.ui.tuner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import io.github.warahiko.mus.R
import io.github.warahiko.mus.ui.component.MusMainTopBar
import io.github.warahiko.mus.ui.theme.MusAppTheme
import io.github.warahiko.mus.ui.util.PreviewThemes

@Composable
fun TunerScreen(
    modifier: Modifier = Modifier,
    onClickGoToOscillator: () -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MusMainTopBar(
                actions = {
                    IconButton(onClick = onClickGoToOscillator) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_play),
                            contentDescription = null,
                        )
                    }
                }
            )
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
        }
    }
}

@PreviewThemes
@Composable
private fun TunerScreenPreview() {
    MusAppTheme {
        TunerScreen()
    }
}
