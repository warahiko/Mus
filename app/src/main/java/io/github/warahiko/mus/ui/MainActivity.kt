package io.github.warahiko.mus.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import io.github.warahiko.mus.ui.theme.MusAppTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MusAppTheme {
                MainContent(startDestination = BottomNavigationScreen.Tuner)
            }
        }
    }

    /**
     * A native method that is implemented by the 'mus' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String
}
