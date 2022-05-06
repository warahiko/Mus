package io.github.warahiko.mus.ui.tuner.oscillator

import android.content.res.Configuration
import androidx.compose.foundation.ScrollState
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.warahiko.mus.R
import io.github.warahiko.mus.ui.component.MusTopBar
import io.github.warahiko.mus.ui.theme.MusAppTheme
import io.github.warahiko.mus.ui.tuner.oscillator.notename.NoteName
import io.github.warahiko.mus.ui.tuner.oscillator.section.NoteNameSection
import io.github.warahiko.mus.ui.tuner.oscillator.section.PitchSection

@Composable
fun OscillatorScreen(
    modifier: Modifier = Modifier,
    viewModel: OscillatorViewModel = hiltViewModel(),
    onBackPressed: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsState()
    OscillatorScreenContent(
        uiState = uiState,
        modifier = modifier,
        onBackPressed = onBackPressed,
        onClickPlayerButton = viewModel::togglePlaying,
        onChangeNoteName = viewModel::onChangeNoteName,
        onChangeOctave = viewModel::onChangeOctave,
        onChangeA4Frequency = viewModel::onChangeA4Frequency,
    )
}

@Composable
private fun OscillatorScreenContent(
    uiState: OscillatorUiState,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    onBackPressed: () -> Unit = {},
    onClickPlayerButton: () -> Unit = {},
    onChangeNoteName: (NoteName) -> Unit = {},
    onChangeOctave: (Float) -> Unit = {},
    onChangeA4Frequency: (Float) -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MusTopBar(
                title = stringResource(id = R.string.oscillator_title),
                onBackPressed = onBackPressed,
            )
        },
        floatingActionButton = {
            LargeFloatingActionButton(onClick = onClickPlayerButton) {
                val iconRes = if (uiState.isPlaying) R.drawable.ic_stop else R.drawable.ic_play
                Icon(
                    painter = painterResource(iconRes),
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
                selectedNoteName = uiState.selectedNoteName,
                onClickButton = onChangeNoteName,
            )
            Spacer(modifier = Modifier.height(16.dp))
            PitchSection(
                octave = uiState.octave,
                octaveText = uiState.roundedOctave.toString(),
                a4Frequency = uiState.a4Frequency,
                a4FrequencyText = uiState.roundedA4Frequency.toString(),
                onChangeOctave = onChangeOctave,
                onChangeA4Frequency = onChangeA4Frequency,
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OscillatorScreenPreview() {
    MusAppTheme {
        OscillatorScreenContent(
            uiState = OscillatorUiState(
                selectedNoteName = NoteName.C,
                octave = 4f,
                a4Frequency = 442f,
                isPlaying = false,
            )
        )
    }
}
