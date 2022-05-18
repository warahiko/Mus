package io.github.warahiko.mus.ui.tuner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.warahiko.mus.R
import io.github.warahiko.mus.ui.component.MusMainTopBar
import io.github.warahiko.mus.ui.theme.MusAppTheme
import io.github.warahiko.mus.ui.util.PreviewThemes

@Composable
fun TunerScreen(
    modifier: Modifier = Modifier,
    onClickGoToOscillator: () -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MusMainTopBar(
                actions = {
                    IconButton(onClick = onClickGoToOscillator) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_play),
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 20.dp)
                .padding(top = 12.dp, bottom = 24.dp)
                .fillMaxSize(),
        ) {
            Text(
                text = stringResource(id = R.string.tuner_a4_frequency, 442),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(60.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(id = R.string.tuner_cent, "+21"),
                    style = MaterialTheme.typography.headlineLarge,
                )
            }
            TunerMeter(
                valueProvider = { 21f },
                modifier = Modifier.fillMaxWidth(),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = "C#",
                    fontSize = 128.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.displayLarge,
                )
                Text(
                    text = "4",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.displayMedium,
                )
            }
        }
    }
}

@PreviewThemes
@Composable
private fun TunerScreenPreview() {
    MusAppTheme {
        TunerScreen()
    }
}
