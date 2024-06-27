@file:SuppressWarnings("MagicNumber", "LongParameterList")

package com.kirchhoff.movies.screen.tvshow.ui.screen.discover.ui

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
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.tvshow.R
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.model.TvShowDiscoverScreenState
import com.kirchhoff.movies.screen.tvshow.ui.view.section.TvShowSectionUI

@Composable
internal fun TvShowDiscoverUI(
    screenState: TvShowDiscoverScreenState,
    onTvShowClick: (UITv) -> Unit,
    onAiringTodayClick: () -> Unit,
    onTheAirClick: () -> Unit,
    onPopularClick: () -> Unit,
    onTopRatedClick: () -> Unit
) {
    Column {
        when {
            screenState.isLoading -> ShowLoading()
            screenState.airingToday.isEmpty() &&
                screenState.onTheAir.isEmpty() &&
                screenState.popular.isEmpty() &&
                screenState.topRated.isEmpty() -> ShowError()
            else -> ShowUI(
                screenState = screenState,
                onTvShowClick = onTvShowClick,
                onAiringTodayClick = onAiringTodayClick,
                onTheAirClick = onTheAirClick,
                onPopularClick = onPopularClick,
                onTopRatedClick = onTopRatedClick
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
    val error = stringResource(R.string.empty_tw_shows)
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
    screenState: TvShowDiscoverScreenState,
    onTvShowClick: (UITv) -> Unit,
    onAiringTodayClick: () -> Unit,
    onTheAirClick: () -> Unit,
    onPopularClick: () -> Unit,
    onTopRatedClick: () -> Unit
) {
    val isAiringTodayVisible = screenState.airingToday.isNotEmpty()
    val isOnTheAirVisible = screenState.onTheAir.isNotEmpty()
    val isPopularVisible = screenState.popular.isNotEmpty()
    val isTopRatedVisible = screenState.topRated.isNotEmpty()

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        if (isAiringTodayVisible) {
            TvShowSectionUI(
                tvShows = screenState.airingToday,
                title = StringValue.IdText(R.string.tv_show_airing_today),
                additionalTopMargin = 8.dp,
                onItemClick = onTvShowClick,
                onSeeAllClick = onAiringTodayClick
            )
        }
        if (isOnTheAirVisible) {
            TvShowSectionUI(
                tvShows = screenState.onTheAir,
                title = StringValue.IdText(R.string.tv_show_on_the_air),
                additionalTopMargin = 8.dp,
                onItemClick = onTvShowClick,
                onSeeAllClick = onTheAirClick
            )
        }
        if (isPopularVisible) {
            TvShowSectionUI(
                tvShows = screenState.popular,
                title = StringValue.IdText(R.string.tv_show_popular),
                additionalTopMargin = 8.dp,
                onItemClick = onTvShowClick,
                onSeeAllClick = onPopularClick
            )
        }
        if (isTopRatedVisible) {
            TvShowSectionUI(
                tvShows = screenState.topRated,
                title = StringValue.IdText(R.string.tv_show_top_rated),
                additionalTopMargin = 8.dp,
                onItemClick = onTvShowClick,
                onSeeAllClick = onTopRatedClick
            )
        }
    }
}

@Preview
@Composable
private fun TvShowDiscoverUIPreview() {
    TvShowDiscoverUI(
        screenState = TvShowDiscoverScreenState(
            airingToday = emptyList(),
            onTheAir = emptyList(),
            popular = emptyList(),
            topRated = emptyList(),
            isLoading = false
        ),
        onTvShowClick = {},
        onAiringTodayClick = {},
        onTheAirClick = {},
        onPopularClick = {},
        onTopRatedClick = {}
    )
}
