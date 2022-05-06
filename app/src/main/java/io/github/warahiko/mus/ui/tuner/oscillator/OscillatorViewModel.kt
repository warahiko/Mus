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
class OscillatorViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(
        OscillatorUiState(
            selectedNoteName = NoteName.C,
            octave = 4f,
            a4Frequency = 442f,
        )
    )
    val uiState: StateFlow<OscillatorUiState> = _uiState.asStateFlow()

    fun onChangeNoteName(noteName: NoteName) {
        _uiState.update {
            it.copy(selectedNoteName = noteName)
        }
    }

    fun onChangeOctave(octave: Float) {
        _uiState.update {
            it.copy(octave = octave)
        }
    }

    fun onChangeA4Frequency(a4Frequency: Float) {
        _uiState.update {
            it.copy(a4Frequency = a4Frequency)
        }
    }
}
