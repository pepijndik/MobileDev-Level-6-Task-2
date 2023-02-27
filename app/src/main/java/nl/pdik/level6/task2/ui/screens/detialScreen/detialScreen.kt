package nl.pdik.level6.task2.ui.screens.detialScreen

import android.annotation.SuppressLint
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import nl.pdik.level6.task2.data.model.Movie
import nl.pdik.level6.task2.ui.screens.overviewScreen.SearchView
import nl.pdik.level6.task2.viewModel.MoviesViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import nl.pdik.level6.task2.R
import nl.pdik.level6.task2.ui.screens.overviewScreen.MoviePoster


@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun MovieDetailScreen(moviesViewModel: MoviesViewModel, navHostController: NavHostController) {
    val movie = moviesViewModel.selectedMovie!!;
    val path = "https://image.tmdb.org/t/p/w300/" + movie.backdrop
    Scaffold(
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MovieBackDrop(path, movie.title)
            MovieInfo(movie, modifier = Modifier.padding(top = 12.dp))

        }
    }
}

@Composable
private fun MovieBackDrop(url: String, name: String) {
    AsyncImage(
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            //.memoryCachePolicy(CachePolicy.ENABLED)
            .build(),
        contentDescription = name
    )
}


@Composable
private fun MovieInfo(movie: Movie, modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 4.dp, top = 4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val poster = "https://image.tmdb.org/t/p/w780/" + movie.poster;
            MoviePoster(
                poster, movie.title,
                Modifier
                    .width(120.dp)
                    .height(160.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            MovieName(name = movie.title)

        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            MovieRating(movie.vote_count)
        }
        MoviePlot(movie.overview!!)
    }
}

@Composable
private fun MovieName(name: String) = Text(
    text = name,
    style = MaterialTheme.typography.subtitle1.copy(
        color = Color.Black,
        letterSpacing = 1.5.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.W500
    ),
    modifier = Modifier.padding(start = 6.dp),
    maxLines = 1,
    overflow = TextOverflow.Ellipsis
)

@Composable
private fun MovieRating(rating: Int) {
    Column(modifier = Modifier.padding(top = 10.dp, start = 4.dp)) {
        Row() {
            Icon(
                Icons.Default.Star,
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
            )
            MovieName(rating.toString())
        }
    }
}

@Composable
private fun MoviePlot(overview: String) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(id = R.string.overview),
            style = MaterialTheme.typography.h6.copy(
                color = Color.Black,
                letterSpacing = 1.5.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W600
            ),
        )

        Text(
            text = overview,
            style = MaterialTheme.typography.subtitle1.copy(
                color = Color.Black,
                letterSpacing = 1.5.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W500
            ),
            modifier = Modifier
                .padding(horizontal = 4.dp),
            overflow = TextOverflow.Ellipsis
        )
    }

}