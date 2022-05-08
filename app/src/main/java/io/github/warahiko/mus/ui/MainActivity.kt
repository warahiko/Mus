package io.github.warahiko.mus.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.warahiko.mus.ui.theme.MusAppTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MusAppTheme {
                MainContent(startDestination = BottomNavigationScreen.Tuner)
            }
        }
    }
}
