package nl.pdik.level6.task2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.pdik.level6.task2.ui.screens.MovieScreens
import nl.pdik.level6.task2.ui.theme.Task2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Task2Theme {
                // A surface container using the 'background' color from the theme
               Level6Task2App()
            }
        }
    }
}

@Composable
fun Level6Task2App() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        },
    ) { innerPadding ->
        NavHost(navController, innerPadding)
    }

}


@Composable
private fun NavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController,
        startDestination = MovieScreens.OverviewScreen.route,
        Modifier.padding(innerPadding)
    ) {
        composable(MovieScreens.OverviewScreen.route) {  }
        composable(MovieScreens.DetialScreen.route) {  }
    }
}
