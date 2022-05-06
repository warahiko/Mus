package io.github.warahiko.mus

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MusApplication : Application() {
    init {
        System.loadLibrary("mus")
    }
}
