package nl.pdik.level6.task2.ui.screens

import androidx.annotation.StringRes
import nl.pdik.level6.task2.R

sealed class MovieScreens(val route: String) {
    object OverviewScreen : MovieScreens("overview")
    object DetialScreen : MovieScreens("detial")
}
