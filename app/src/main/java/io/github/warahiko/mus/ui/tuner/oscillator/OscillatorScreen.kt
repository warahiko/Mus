package io.github.warahiko.mus.ui.tuner.oscillator

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.warahiko.mus.R
import io.github.warahiko.mus.ui.component.MusTopBar
import io.github.warahiko.mus.ui.theme.MusAppTheme
import io.github.warahiko.mus.ui.tuner.oscillator.notename.NoteName
import io.github.warahiko.mus.ui.tuner.oscillator.section.NoteNameSection
import io.github.warahiko.mus.ui.tuner.oscillator.section.PitchSection

@Composable
fun OscillatorScreen(
    modifier: Modifier = Modifier,
) {
    val (selectedNoteName, setNoteName) = remember { mutableStateOf(NoteName.C) }
    val (octave, setOctave) = remember { mutableStateOf(4f) }
    val (a4Frequency, setA4Frequency) = remember { mutableStateOf(442f) }

    val scrollState = rememberScrollState()
    Scaffold(
        modifier = modifier,
        topBar = {
            MusTopBar(
                title = stringResource(id = R.string.oscillator_title),
            )
        },
        floatingActionButton = {
            LargeFloatingActionButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_play),
                    contentDescription = null,
                )
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 20.dp)
                .padding(top = 12.dp, bottom = 24.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            NoteNameSection(
                selectedNoteName = selectedNoteName,
                onClickButton = setNoteName
            )
            Spacer(modifier = Modifier.height(16.dp))
            PitchSection(
                octave = octave,
                a4Frequency = a4Frequency,
                onChangeOctave = setOctave,
                onChangeA4Frequency = setA4Frequency,
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OscillatorScreenPreview() {
    MusAppTheme {
        OscillatorScreen()
    }
}
