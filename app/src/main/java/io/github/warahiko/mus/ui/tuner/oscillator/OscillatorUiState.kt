package io.github.warahiko.mus.ui.tuner.oscillator

import androidx.compose.runtime.Immutable
import io.github.warahiko.mus.ui.tuner.oscillator.notename.NoteName

@Immutable
data class OscillatorUiState(
    val selectedNoteName: NoteName,
    val octave: Float,
    val a4Frequency: Float,
)
