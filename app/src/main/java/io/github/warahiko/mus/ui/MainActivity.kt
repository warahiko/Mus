package io.github.warahiko.mus.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.warahiko.mus.R
import io.github.warahiko.mus.ui.theme.MusAppTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MusAppTheme {
                Content(stringFromJNI())
            }
        }
    }

    /**
     * A native method that is implemented by the 'mus' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String
}

@Composable
fun Content(
    text: String,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = text,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}
