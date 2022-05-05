package io.github.warahiko.mus.ui.component

import android.content.res.Configuration
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.warahiko.mus.R
import io.github.warahiko.mus.ui.theme.MusAppTheme

@Composable
fun MusMainTopBar(
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
            )
        },
        modifier = modifier,
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MusMainTopBarPreview() {
    MusAppTheme {
        MusMainTopBar()
    }
}
