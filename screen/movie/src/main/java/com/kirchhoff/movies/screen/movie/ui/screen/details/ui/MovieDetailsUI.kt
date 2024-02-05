package com.kirchhoff.movies.screen.movie.ui.screen.details.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.data.UIGenre
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.movie.data.UICountry
import com.kirchhoff.movies.screen.movie.data.UIMovieInfo
import com.kirchhoff.movies.screen.movie.data.UITrailer
import com.kirchhoff.movies.screen.movie.data.UITrailersList
import com.kirchhoff.movies.screen.movie.ui.screen.details.model.MovieDetailsScreenState
import com.kirchhoff.movies.screen.movie.ui.screen.details.ui.credits.MovieDetailsCreditsUI
import com.kirchhoff.movies.screen.movie.ui.screen.details.ui.info.MovieDetailsInfoUI
import com.kirchhoff.movies.screen.movie.ui.screen.details.ui.info.trailers.MovieDetailsTrailersUI

@ExperimentalLayoutApi
@Composable
internal fun MovieDetailsUI(
    screenState: MovieDetailsScreenState,
    onBackPressed: () -> Unit,
    onProductionCountryClick: (UICountry) -> Unit,
    onGenreClick: (UIGenre) -> Unit,
    onTrailerClick: (UITrailer) -> Unit,
    onCreditItemClick: (UIEntertainmentPerson) -> Unit
) {
    Column {
        MoviesToolbar(
            title = screenState.title.asString(LocalContext.current),
            onBackPressed = { onBackPressed.invoke() }
        )

        when {
            screenState.isLoading -> ShowLoading()
            screenState.errorMessage.asString(LocalContext.current).isNotEmpty() -> ShowError(screenState)
            else -> ShowUI(
                screenState = screenState,
                onProductionCountryClick = onProductionCountryClick,
                onGenreClick = onGenreClick,
                onTrailerClick = onTrailerClick,
                onCreditItemClick = onCreditItemClick
            )
        }
    }
}

@Composable
private fun ShowLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ShowError(screenState: MovieDetailsScreenState) {
    val context = LocalContext.current
    val error = screenState.errorMessage.asString(context)
    var errorMessage by rememberSaveable { mutableStateOf("") }

    if (error.isNotEmpty() && errorMessage.isEmpty()) {
        errorMessage = error
        SideEffect {
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }
}

@ExperimentalLayoutApi
@Composable
private fun ShowUI(
    screenState: MovieDetailsScreenState,
    onProductionCountryClick: (UICountry) -> Unit,
    onGenreClick: (UIGenre) -> Unit,
    onTrailerClick: (UITrailer) -> Unit,
    onCreditItemClick: (UIEntertainmentPerson) -> Unit
) {
    val creditsVisible = screenState.credits.cast?.isNotEmpty() == true || screenState.credits.crew?.isNotEmpty() == true

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        AsyncImage(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            model = BASE_POSTER_PATH + screenState.backdropPath,
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp),
            text = screenState.title.asString(LocalContext.current),
            color = colorResource(R.color.text_main),
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        MovieDetailsInfoUI(
            info = screenState.info,
            posterPath = screenState.posterPath,
            onProductionCountryClick = onProductionCountryClick,
            onGenreClick = onGenreClick
        )
        if (screenState.trailers.results.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            MovieDetailsTrailersUI(
                trailers = screenState.trailers,
                onTrailerClick = onTrailerClick
            )
        }
        if (creditsVisible) {
            Spacer(modifier = Modifier.height(8.dp))
            MovieDetailsCreditsUI(
                credits = screenState.credits,
                onItemClick = onCreditItemClick
            )
        }
    }
}

@ExperimentalLayoutApi
@Preview
@Composable
private fun MovieDetailsUIPreview() {
    MovieDetailsUI(
        screenState = MovieDetailsScreenState(
            title = StringValue.SimpleText(""),
            backdropPath = "",
            posterPath = "",
            info = UIMovieInfo(
                productionCountries = emptyList(),
                runtime = 0,
                tagLine = "",
                overview = "",
                releaseDate = "",
                voteCount = 0,
                voteAverage = 0f,
                genres = emptyList()
            ),
            trailers = UITrailersList(
                results = emptyList()
            ),
            credits = UIEntertainmentCredits(
                cast = emptyList(),
                crew = emptyList()
            ),
            similarMovies = emptyList(),
            images = emptyList(),
            isLoading = false,
            errorMessage = StringValue.Empty
        ),
        onBackPressed = {},
        onProductionCountryClick = {},
        onGenreClick = {},
        onTrailerClick = {},
        onCreditItemClick = {}
    )
}
