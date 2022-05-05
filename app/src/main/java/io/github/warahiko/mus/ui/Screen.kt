package io.github.warahiko.mus.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import io.github.warahiko.mus.R

sealed interface Screen {
    val parent: Screen?
    val relativeRoute: String

    val route: String
        get() = listOfNotNull(
            parent?.route,
            relativeRoute,
        )
            .filter { it.isNotEmpty() }
            .joinToString(separator = "/")

    sealed interface SubgraphRoot : Screen {
        val subgraphRoute: String
            get() = "$relativeRoute-subgraphRoute"
    }

    object Root : Screen {
        override val parent: Screen? = null
        override val relativeRoute: String = ""
    }

    object Oscillator : Screen {
        override val parent: Screen = BottomNavigationScreen.Tuner
        override val relativeRoute: String = "oscillator"
    }
}

sealed interface BottomNavigationScreen : Screen.SubgraphRoot {
    override val parent: Screen?
        get() = Screen.Root

    @get:StringRes
    val labelRes: Int

    @get:DrawableRes
    val iconRes: Int

    object Tuner : BottomNavigationScreen {
        override val relativeRoute: String = "tuner"
        override val labelRes: Int = R.string.tuner_label
        override val iconRes: Int = R.drawable.ic_tuner
    }

    object Setting : BottomNavigationScreen {
        override val relativeRoute: String = "setting"
        override val labelRes: Int = R.string.setting_label
        override val iconRes: Int = R.drawable.ic_settings
    }

    companion object {
        val items: List<BottomNavigationScreen> = listOf(
            Tuner,
            Setting,
        )
    }
}
