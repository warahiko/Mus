package io.github.warahiko.mus

import android.app.Application

class MusApplication : Application() {
    init {
        System.loadLibrary("mus")
    }
}
