package io.github.warahiko.mus.ui.tuner.oscillator

import javax.inject.Inject

class Oscillator @Inject constructor() {
    external fun setNoteNumber(noteNumber: Int)
    external fun setA4Frequency(a4Frequency: Int)
    external fun play()
    external fun stop()
}
