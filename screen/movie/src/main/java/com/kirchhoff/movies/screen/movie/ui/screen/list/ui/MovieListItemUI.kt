package com.kirchhoff.movies.screen.movie.ui.screen.list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.keywordsview.RatingText

@SuppressWarnings("MagicNumber")
@Composable
internal fun MovieListItemUI(
    movie: UIMovie,
    onMovieClick: (UIMovie) -> Unit
) {
    val cornerRadius = 8.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = { onMovieClick.invoke(movie) }
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            AsyncImage(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .shadow(10.dp)
                    .clip(shape = RoundedCornerShape(cornerRadius)),
                model = BASE_POSTER_PATH + movie.posterPath,
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
            RatingText(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                voteAverage = movie.voteAverage
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.height(50.dp),
            textAlign = TextAlign.Center,
            text = movie.title,
            color = Colors.TextMain,
            fontSize = 16.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@SuppressWarnings("MagicNumber")
@Preview
@Composable
private fun MovieListItemUIPreview() {
    val movie = UIMovie(
        id = MovieId(100),
        title = "Super movie",
        posterPath = null,
        backdropPath = null,
        voteAverage = 9.5f
    )
    MovieListItemUI(movie) {}
}
