package io.github.warahiko.mus.ui.tuner

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.warahiko.mus.ui.BottomNavigationScreen
import io.github.warahiko.mus.ui.Screen
import io.github.warahiko.mus.ui.tuner.oscillator.OscillatorScreen

fun NavGraphBuilder.tunerGraph(navController: NavController) {
    navigation(
        startDestination = BottomNavigationScreen.Tuner.route,
        route = BottomNavigationScreen.Tuner.subgraphRoute,
    ) {
        composable(route = BottomNavigationScreen.Tuner.route) {
            TunerScreen(
                onClickGoToOscillator = {
                    navController.navigate(route = Screen.Oscillator.route)
                }
            )
        }
        composable(route = Screen.Oscillator.route) {
            OscillatorScreen()
        }
    }
}
