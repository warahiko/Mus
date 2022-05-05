package io.github.warahiko.mus.ui.component

import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.warahiko.mus.ui.theme.MusAppTheme

@Composable
fun MusTopBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    SmallTopAppBar(
        title = {
            Text(
                text = title,
            )
        },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun MusTopBarPreview() {
    MusAppTheme {
        MusTopBar(title = "preview")
    }
}
