package io.github.warahiko.mus.ui.tuner.oscillator

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.warahiko.mus.ui.tuner.oscillator.notename.NoteName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OscillatorViewModel @Inject constructor(
    private val oscillator: Oscillator,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        OscillatorUiState(
            selectedNoteName = NoteName.C,
            octave = 4f,
            a4Frequency = 442f,
            isPlaying = false,
        )
    )
    val uiState: StateFlow<OscillatorUiState> = _uiState.asStateFlow()

    fun setupOscillator() {
        val state = _uiState.value
        oscillator.setNoteNumber(state.noteNumber)
        oscillator.setA4Frequency(state.roundedA4Frequency)
    }

    fun onChangeNoteName(noteName: NoteName) {
        _uiState.update { state ->
            state.copy(selectedNoteName = noteName).also {
                oscillator.setNoteNumber(it.noteNumber)
            }
        }
    }

    fun onChangeOctave(octave: Float) {
        _uiState.update { state ->
            state.copy(octave = octave).also {
                oscillator.setNoteNumber(it.noteNumber)
            }
        }
    }

    fun onChangeA4Frequency(a4Frequency: Float) {
        _uiState.update { state ->
            state.copy(a4Frequency = a4Frequency).also {
                oscillator.setA4Frequency(it.roundedA4Frequency)
            }
        }
    }

    fun togglePlaying() {
        _uiState.update { state ->
            state.copy(isPlaying = !state.isPlaying).also {
                if (it.isPlaying) {
                    oscillator.play()
                } else {
                    oscillator.stop()
                }
            }
        }
    }

    private val OscillatorUiState.noteNumber: Int
        get() = 12 * (roundedOctave + 1) + selectedNoteName.ordinal
}
