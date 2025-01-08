@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.movie.ui.screen.details.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.data.ui.UIEntertainmentPerson
import com.kirchhoff.movies.core.data.ui.UIGenre
import com.kirchhoff.movies.core.data.ui.UIImage
import com.kirchhoff.movies.core.data.ui.UIMovie
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.movie.data.UICountry
import com.kirchhoff.movies.screen.movie.data.UIProductionCompany
import com.kirchhoff.movies.screen.movie.data.UITrailer
import com.kirchhoff.movies.screen.movie.ui.screen.details.model.MovieDetailsScreenState
import com.kirchhoff.movies.screen.movie.ui.screen.details.ui.companies.MovieDetailsProductionCompaniesUI
import com.kirchhoff.movies.screen.movie.ui.screen.details.ui.credits.MovieDetailsCreditsUI
import com.kirchhoff.movies.screen.movie.ui.screen.details.ui.genre.MovieDetailsGenresUI
import com.kirchhoff.movies.screen.movie.ui.screen.details.ui.images.MovieDetailsImagesUI
import com.kirchhoff.movies.screen.movie.ui.screen.details.ui.info.MovieDetailsInfoUI
import com.kirchhoff.movies.screen.movie.ui.screen.details.ui.info.trailers.MovieDetailsTrailersUI
import com.kirchhoff.movies.screen.movie.ui.view.section.MovieSectionUI

@SuppressWarnings("LongParameterList")
@ExperimentalLayoutApi
@Composable
internal fun MovieDetailsUI(
    screenState: MovieDetailsScreenState,
    onBackPressed: () -> Unit,
    onProductionCountryClick: (UICountry) -> Unit,
    onGenreClick: (UIGenre) -> Unit,
    onTrailerClick: (UITrailer) -> Unit,
    onCreditItemClick: (UIEntertainmentPerson) -> Unit,
    onCastSeeAllClick: () -> Unit,
    onCrewSeeAllClick: () -> Unit,
    onSimilarMovieClick: (UIMovie) -> Unit,
    onSimilarMovieSeeAllClick: () -> Unit,
    onImageItemClick: (UIImage) -> Unit,
    onImageSeeAllClick: () -> Unit,
    onReviewsClick: () -> Unit,
    onProductionCompanyClick: (UIProductionCompany) -> Unit
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
                onCreditItemClick = onCreditItemClick,
                onCastSeeAllClick = onCastSeeAllClick,
                onCrewSeeAllClick = onCrewSeeAllClick,
                onSimilarMovieClick = onSimilarMovieClick,
                onSimilarMovieSeeAllClick = onSimilarMovieSeeAllClick,
                onImageItemClick = onImageItemClick,
                onImageSeeAllClick = onImageSeeAllClick,
                onReviewsClick = onReviewsClick,
                onProductionCompanyClick = onProductionCompanyClick
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

@SuppressWarnings("LongParameterList", "LongMethod")
@ExperimentalLayoutApi
@Composable
private fun ShowUI(
    screenState: MovieDetailsScreenState,
    onProductionCountryClick: (UICountry) -> Unit,
    onGenreClick: (UIGenre) -> Unit,
    onTrailerClick: (UITrailer) -> Unit,
    onCreditItemClick: (UIEntertainmentPerson) -> Unit,
    onCastSeeAllClick: () -> Unit,
    onCrewSeeAllClick: () -> Unit,
    onSimilarMovieClick: (UIMovie) -> Unit,
    onSimilarMovieSeeAllClick: () -> Unit,
    onImageItemClick: (UIImage) -> Unit,
    onImageSeeAllClick: () -> Unit,
    onReviewsClick: () -> Unit,
    onProductionCompanyClick: (UIProductionCompany) -> Unit
) {
    val creditsVisible = screenState.credits.cast?.isNotEmpty() == true || screenState.credits.crew?.isNotEmpty() == true
    val similarMoviesVisible = screenState.similarMovies.isNotEmpty()
    val imagesVisible = screenState.images.isNotEmpty()
    val trailersVisible = screenState.trailers.isNotEmpty()
    val productionCompaniesVisible = screenState.info.productionCompanies.isNotEmpty()

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
            color = Colors.TextMain,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        MovieDetailsInfoUI(
            info = screenState.info,
            posterPath = screenState.posterPath,
            onProductionCountryClick = onProductionCountryClick
        )
        Spacer(modifier = Modifier.height(8.dp))
        MovieDetailsGenresUI(
            genres = screenState.info.genres,
            onGenreClick = onGenreClick
        )
        Text(
            modifier = Modifier.padding(
                top = 8.dp,
                start = 16.dp,
                end = 16.dp
            ),
            text = screenState.info.tagLine.orEmpty(),
            color = Colors.Black,
            fontSize = 20.sp
        )
        Text(
            modifier = Modifier.padding(
                top = 8.dp,
                start = 16.dp,
                end = 16.dp
            ),
            text = screenState.info.overview,
            fontSize = 14.sp,
            color = Colors.Gray
        )
        if (trailersVisible) {
            Spacer(modifier = Modifier.height(8.dp))
            MovieDetailsTrailersUI(
                trailers = screenState.trailers,
                onTrailerClick = onTrailerClick
            )
        }
        if (productionCompaniesVisible) {
            Spacer(modifier = Modifier.height(8.dp))
            MovieDetailsProductionCompaniesUI(
                companies = screenState.info.productionCompanies,
                onItemClick = onProductionCompanyClick
            )
        }
        if (creditsVisible) {
            Spacer(modifier = Modifier.height(8.dp))
            MovieDetailsCreditsUI(
                credits = screenState.credits,
                onItemClick = onCreditItemClick,
                onCastSeeAllClick = onCastSeeAllClick,
                onCrewSeeAllClick = onCrewSeeAllClick
            )
        }
        if (similarMoviesVisible) {
            Spacer(modifier = Modifier.height(8.dp))
            MovieSectionUI(
                movies = screenState.similarMovies,
                title = screenState.similarMoviesTitle,
                onItemClick = onSimilarMovieClick,
                onSeeAllClick = onSimilarMovieSeeAllClick
            )
        }
        if (imagesVisible) {
            Spacer(modifier = Modifier.height(8.dp))
            MovieDetailsImagesUI(
                images = screenState.images,
                onItemClick = onImageItemClick,
                onSeeAllClick = onImageSeeAllClick
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = { onReviewsClick.invoke() }
                )
                .padding(16.dp),
            text = StringValue
                .IdText(com.kirchhoff.movies.screen.movie.R.string.movie_reviews)
                .asString(LocalContext.current),
            color = Colors.TextMain,
            fontSize = 18.sp
        )
    }
}

@ExperimentalLayoutApi
@Preview
@Composable
private fun MovieDetailsUIPreview() {
    MovieDetailsUI(
        screenState = MovieDetailsScreenState.Default,
        onBackPressed = {},
        onProductionCountryClick = {},
        onGenreClick = {},
        onTrailerClick = {},
        onCreditItemClick = {},
        onCastSeeAllClick = {},
        onCrewSeeAllClick = {},
        onSimilarMovieClick = {},
        onSimilarMovieSeeAllClick = {},
        onImageItemClick = {},
        onImageSeeAllClick = {},
        onReviewsClick = {},
        onProductionCompanyClick = {}
    )
}
