package io.github.warahiko.mus.ui.component

import android.content.res.Configuration
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.github.warahiko.mus.ui.BottomNavigationScreen
import io.github.warahiko.mus.ui.theme.MusAppTheme

@Composable
fun MusNavigationBar(
    currentDestinationRoutes: List<String?>?,
    modifier: Modifier = Modifier,
    onClickItem: (screen: BottomNavigationScreen) -> Unit = {},
) {
    NavigationBar(
        modifier = modifier,
    ) {
        BottomNavigationScreen.items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(screen.iconRes),
                        contentDescription = stringResource(screen.labelRes),
                    )
                },
                label = {
                    Text(
                        text = stringResource(screen.labelRes),
                    )
                },
                selected = currentDestinationRoutes?.any { screen.subgraphRoute == it } == true,
                onClick = { onClickItem(screen) },
            )
        }
    }
}

internal class MusNavigationBarCurrentDestinationRoutesProvider : PreviewParameterProvider<List<String?>> {
    override val values: Sequence<List<String?>>
        get() = BottomNavigationScreen.items
            .map { listOf(it.route) }
            .asSequence()
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MusNavigationBarPreview(
    @PreviewParameter(MusNavigationBarCurrentDestinationRoutesProvider::class)
    currentDestinationRoutes: List<String?>,
) {
    MusAppTheme {
        MusNavigationBar(currentDestinationRoutes = currentDestinationRoutes)
    }
}
