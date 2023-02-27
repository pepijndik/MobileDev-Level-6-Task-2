package nl.pdik.level6.task2.ui.screens.overviewScreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.pdik.level6.task2.R
import nl.pdik.level6.task2.data.api.util.Resource
import nl.pdik.level6.task2.data.model.Movie
import nl.pdik.level6.task2.ui.screens.MovieScreens
import nl.pdik.level6.task2.viewModel.MoviesViewModel
import kotlin.reflect.typeOf

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OverviewScreen(viewModel: MoviesViewModel) {
    val context = LocalContext.current
    viewModel.getMovies();

    val moviesResource: Resource<List<Movie>>? by viewModel.moviesResource.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
            ) {
                SearchView(viewModel)
            }
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            when (moviesResource) {
                is Resource.Success -> {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(128.dp),
                    ) {
                        //TODO: show items for movies
                        val movies = (moviesResource as Resource.Success<List<Movie>>).data;
                        items(items = movies!!, itemContent = { movie: Movie ->
                            MovieCard(movie)
                        })
                    }
                }
                is Resource.Error -> {
                    //TODO: show text for error state, use the .message of the resource
                    if ((moviesResource as Resource.Error<List<Movie>>).message != null) {
                        Text(text = moviesResource?.message!!)
                    }
                }
                is Resource.Loading<*> -> {
                    //TODO: show CircularProgressIndicator
                    CircularProgressIndicator()
                }
                else -> {
                    //TODO: Show state?
                     Text(text = moviesResource!!::class.java.typeName)
                }
            }
        }

    }

}

@Composable
fun MovieCard(movie: Movie) {
    Card() {

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchView(viewModel: MoviesViewModel) {
    val searchQueryState = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = searchQueryState.value,
        onValueChange = { value ->
            searchQueryState.value = value

        },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            IconButton(onClick = {
                //TODO: Your logic here

                //based on @ExperimentalComposeUiApi - if this doesn't work in a newer version remove it
                //no alternative in compose for hiding keyboard at time of writing
                keyboardController?.hide()
            }) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(24.dp)
                )
            }
        },
        trailingIcon = {
            if (searchQueryState.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        searchQueryState.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search_movie_hint),
                color = Color.White
            )
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = MaterialTheme.colors.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}