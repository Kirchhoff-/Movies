package com.kirchhoff.movies.screen.tvshow.ui.screen.details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.model.TvShowDetailsScreenState

@Composable
internal fun TvShowDetailsUI(
    screenState: TvShowDetailsScreenState
) {
    Column {
        MoviesToolbar(
            title = screenState.title,
            onBackPressed = {}
        )
    }
}
