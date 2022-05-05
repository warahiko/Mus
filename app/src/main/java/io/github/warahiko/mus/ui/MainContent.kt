package io.github.warahiko.mus.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.warahiko.mus.ui.component.MusNavigationBar
import io.github.warahiko.mus.ui.setting.settingGraph
import io.github.warahiko.mus.ui.tuner.tunerGraph

@Composable
fun MainContent(
    startDestination: BottomNavigationScreen,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            MusNavigationBar(
                currentDestinationRoutes = navBackStackEntry?.destination?.hierarchy?.map { it.route }?.toList(),
                onClickItem = { screen ->
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination.subgraphRoute,
            modifier = Modifier.padding(contentPadding),
        ) {
            tunerGraph(navController)
            settingGraph()
        }
    }
}
