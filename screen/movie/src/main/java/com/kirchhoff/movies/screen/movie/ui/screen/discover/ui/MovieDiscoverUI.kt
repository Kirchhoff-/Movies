@file:SuppressWarnings("MagicNumber", "LongParameterList")

package com.kirchhoff.movies.screen.movie.ui.screen.discover.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.ui.screen.discover.model.MovieDiscoverScreenState
import com.kirchhoff.movies.screen.movie.ui.view.section.MovieSectionUI

@Composable
internal fun MovieDiscoverUI(
    screenState: MovieDiscoverScreenState,
    onMovieClick: (UIMovie) -> Unit,
    onNowPlayingClick: () -> Unit,
    onUpcomingClick: () -> Unit,
    onPopularClick: () -> Unit,
    onTopRatedClick: () -> Unit
) {
    Column {
        when {
            screenState.isLoading -> ShowLoading()
            screenState.nowPlaying.isEmpty() &&
                screenState.popular.isEmpty() &&
                screenState.upcoming.isEmpty() &&
                screenState.topRated.isEmpty() -> ShowError()
            else -> ShowUI(
                screenState = screenState,
                onMovieClick = onMovieClick,
                onNowPlayingClick = onNowPlayingClick,
                onPopularClick = onPopularClick,
                onTopRatedClick = onTopRatedClick,
                onUpcomingClick = onUpcomingClick
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
private fun ShowError() {
    val context = LocalContext.current
    val error = stringResource(R.string.movie_empty_movies)
    var errorMessage by rememberSaveable { mutableStateOf("") }

    if (error.isNotEmpty() && errorMessage.isEmpty()) {
        errorMessage = error
        SideEffect {
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }
}

@Composable
private fun ShowUI(
    screenState: MovieDiscoverScreenState,
    onMovieClick: (UIMovie) -> Unit,
    onNowPlayingClick: () -> Unit,
    onUpcomingClick: () -> Unit,
    onPopularClick: () -> Unit,
    onTopRatedClick: () -> Unit
) {
    val nowPlayingVisible = screenState.nowPlaying.isNotEmpty()
    val upcomingVisible = screenState.upcoming.isNotEmpty()
    val popularVisible = screenState.popular.isNotEmpty()
    val topRatedVisible = screenState.topRated.isNotEmpty()

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        if (nowPlayingVisible) {
            MovieSectionUI(
                movies = screenState.nowPlaying,
                title = StringValue.IdText(R.string.movie_now_playing),
                additionalTopMargin = 8.dp,
                onItemClick = onMovieClick,
                onSeeAllClick = onNowPlayingClick
            )
        }
        if (upcomingVisible) {
            MovieSectionUI(
                movies = screenState.upcoming,
                title = StringValue.IdText(R.string.movie_upcoming),
                additionalTopMargin = 8.dp,
                onItemClick = onMovieClick,
                onSeeAllClick = onUpcomingClick
            )
        }
        if (popularVisible) {
            MovieSectionUI(
                movies = screenState.popular,
                title = StringValue.IdText(R.string.movie_popular),
                additionalTopMargin = 8.dp,
                onItemClick = onMovieClick,
                onSeeAllClick = onPopularClick
            )
        }
        if (topRatedVisible) {
            MovieSectionUI(
                movies = screenState.topRated,
                title = StringValue.IdText(R.string.movie_top_rated),
                additionalTopMargin = 8.dp,
                onItemClick = onMovieClick,
                onSeeAllClick = onTopRatedClick
            )
        }
    }
}

@Preview
@Composable
private fun MovieDiscoverUIPreview() {
    MovieDiscoverUI(
        screenState = MovieDiscoverScreenState(
            nowPlaying = emptyList(),
            popular = emptyList(),
            topRated = emptyList(),
            upcoming = emptyList(),
            isLoading = false
        ),
        onMovieClick = {},
        onNowPlayingClick = {},
        onUpcomingClick = {},
        onPopularClick = {},
        onTopRatedClick = {}
    )
}
