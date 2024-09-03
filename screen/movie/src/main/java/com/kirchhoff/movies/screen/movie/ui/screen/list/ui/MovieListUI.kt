package com.kirchhoff.movies.screen.movie.ui.screen.list.ui

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.ui.compose.LazyStaggeredGridScrollHandler
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.screen.movie.ui.screen.list.model.MovieListScreenState

@SuppressWarnings("MagicNumber")
@Composable
internal fun MovieListUI(
    screenState: MovieListScreenState,
    onLoadMore: () -> Unit,
    onMovieClick: (UIMovie) -> Unit,
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current
    val gridState = rememberLazyStaggeredGridState()
    var errorMessage by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        MoviesToolbar(title = screenState.title.asString(context)) {
            onBackPressed.invoke()
        }

        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                state = gridState
            ) {
                items(
                    count = screenState.movieList.size,
                    itemContent = {
                        MovieListItemUI(
                            screenState.movieList[it],
                            onMovieClick
                        )
                    }
                )
            }
            LazyStaggeredGridScrollHandler(gridState = gridState) {
                onLoadMore()
            }
            this@Column.AnimatedVisibility(
                modifier = Modifier.align(Alignment.Center),
                visible = screenState.loadingVisible
            ) {
                CircularProgressIndicator()
            }
            this@Column.AnimatedVisibility(
                modifier = Modifier.align(Alignment.BottomStart),
                visible = screenState.paginationVisible
            ) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
        }
    }

    if (screenState.errorMessage.isNotEmpty() && errorMessage.isEmpty()) {
        errorMessage = screenState.errorMessage
        SideEffect {
            Toast.makeText(context, screenState.errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}

@SuppressWarnings("MagicNumber")
@Preview
@Composable
private fun MovieListUIPreview() {
    MovieListUI(
        screenState = MovieListScreenState.Default,
        onLoadMore = {},
        onMovieClick = {},
        onBackPressed = {}
    )
}
