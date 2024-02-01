package com.kirchhoff.movies.screen.movie.ui.screen.details.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.screen.movie.ui.screen.details.model.MovieDetailsScreenState

@Composable
internal fun MovieDetailsUI(
    screenState: MovieDetailsScreenState,
    onBackPressed: () -> Unit
) {
    Column {
        MoviesToolbar(
            title = screenState.title.asString(LocalContext.current),
            onBackPressed = { onBackPressed.invoke() }
        )

        Box(modifier = Modifier.fillMaxSize())
    }
}
