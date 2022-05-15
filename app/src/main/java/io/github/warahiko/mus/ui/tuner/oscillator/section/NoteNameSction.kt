package io.github.warahiko.mus.ui.tuner.oscillator.section

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.warahiko.mus.ui.theme.MusAppTheme
import io.github.warahiko.mus.ui.tuner.oscillator.notename.NoteName
import io.github.warahiko.mus.ui.util.PreviewThemes
import io.github.warahiko.mus.ui.util.PreviewWidths

@Composable
fun NoteNameSection(
    selectedNoteName: NoteName,
    modifier: Modifier = Modifier,
    onClickButton: (noteName: NoteName) -> Unit = {},
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
                Text(text = "音名")
            }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(100.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = (64 * NoteName.values().size).dp),
            ) {
                items(NoteName.values()) { noteName ->
                    Button(
                        onClick = { onClickButton(noteName) },
                        enabled = noteName != selectedNoteName,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .height(48.dp),
                    ) {
                        Text(text = noteName.text)
                    }
                }
            }
        }
    }
}

@PreviewThemes
@PreviewWidths
@Composable
private fun NoteNameSectionPreview() {
    MusAppTheme {
        NoteNameSection(selectedNoteName = NoteName.C)
    }
}
