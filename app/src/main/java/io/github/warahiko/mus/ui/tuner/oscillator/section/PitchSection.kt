package io.github.warahiko.mus.ui.tuner.oscillator.section

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.warahiko.mus.ui.theme.MusAppTheme
import kotlin.math.roundToInt

@Composable
fun PitchSection(
    octave: Float,
    a4Frequency: Float,
    modifier: Modifier = Modifier,
    onChangeOctave: (octave: Float) -> Unit = {},
    onChangeA4Frequency: (a4Frequency: Float) -> Unit = {},
) {
    Card(
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart,
            ) {
                Text(text = "音高")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "オクターブ")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "${octave.roundToInt()}")
            }
            Slider(
                value = octave,
                onValueChange = {
                    println(it)
                    onChangeOctave(it)
                },
                valueRange = 1f..6f,
                steps = 4,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "A4 の周波数")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "${a4Frequency.roundToInt()} Hz")
            }
            Slider(
                value = a4Frequency,
                onValueChange = {
                    println(it)
                    onChangeA4Frequency(it)
                },
                valueRange = 435f..450f,
                steps = 14,
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PitchSectionPreview() {
    MusAppTheme {
        PitchSection(
            octave = 4f,
            a4Frequency = 442f,
        )
    }
}
