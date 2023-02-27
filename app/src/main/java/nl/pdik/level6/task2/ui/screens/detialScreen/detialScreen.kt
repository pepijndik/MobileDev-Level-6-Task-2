package nl.pdik.level6.task2.ui.screens.detialScreen
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.pdik.level6.task2.data.model.Movie


@Composable
private fun MovieInfo(movie: Movie, modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        MovieName(name = movie.title)
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
//            MovieFeature(Icons.Default.DateRange, movie.releaseDate)
//            MovieFeature(Icons.Default.ThumbUp, movie.voteCount.toString())
        }
    }
}

@Composable
private fun MovieName(name: String) = Text(
    text = name,
    style = MaterialTheme.typography.subtitle1.copy(
        color = Color.White,
        letterSpacing = 1.5.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.W500
    ),
    maxLines = 1,
    overflow = TextOverflow.Ellipsis
)
