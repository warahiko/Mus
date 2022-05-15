package io.github.warahiko.mus.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.warahiko.mus.ui.theme.MusAppTheme
import io.github.warahiko.mus.ui.util.PreviewThemes

@Composable
fun MusTopBar(
    title: String,
    modifier: Modifier = Modifier,
    showBackButton: Boolean = true,
    onBackPressed: () -> Unit = {},
) {
    SmallTopAppBar(
        title = {
            Text(
                text = title,
            )
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
        modifier = modifier,
    )
}

@PreviewThemes
@Composable
private fun MusTopBarPreview() {
    MusAppTheme {
        MusTopBar(title = "preview")
    }
}
