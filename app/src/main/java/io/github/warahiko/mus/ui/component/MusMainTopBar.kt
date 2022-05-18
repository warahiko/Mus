package io.github.warahiko.mus.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import io.github.warahiko.mus.R
import io.github.warahiko.mus.ui.theme.MusAppTheme
import io.github.warahiko.mus.ui.util.PreviewThemes

@Composable
fun MusMainTopBar(
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
            )
        },
        actions = actions,
        modifier = modifier,
    )
}

@PreviewThemes
@Composable
private fun MusMainTopBarPreview() {
    MusAppTheme {
        MusMainTopBar(actions = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = null,
                )
            }
        })
    }
}
