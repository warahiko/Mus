package io.github.warahiko.mus.ui.setting

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.warahiko.mus.ui.BottomNavigationScreen

fun NavGraphBuilder.settingGraph() {
    navigation(
        startDestination = BottomNavigationScreen.Setting.route,
        route = BottomNavigationScreen.Setting.subgraphRoute,
    ) {
        composable(route = BottomNavigationScreen.Setting.route) {
            SettingScreen()
        }
    }
}
