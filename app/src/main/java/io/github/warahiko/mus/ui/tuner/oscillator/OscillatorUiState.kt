package io.github.warahiko.mus.ui.tuner.oscillator

import androidx.compose.runtime.Immutable
import io.github.warahiko.mus.ui.tuner.oscillator.notename.NoteName
import kotlin.math.roundToInt

@Immutable
data class OscillatorUiState(
    val selectedNoteName: NoteName,
    val octave: Float,
    val a4Frequency: Float,
    val isPlaying: Boolean,
) {
    val roundedOctave: Int get() = octave.roundToInt()
    val roundedA4Frequency: Int get() = a4Frequency.roundToInt()
}
