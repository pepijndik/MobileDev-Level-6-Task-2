package nl.pdik.level6.task2.ui.screens


const val MOVIE_ID = "MOVIE_ID"
sealed class MovieScreens(val route: String) {
    object OverviewScreen : MovieScreens("overview")
    object DetialScreen : MovieScreens("movie/{$MOVIE_ID}")

}
