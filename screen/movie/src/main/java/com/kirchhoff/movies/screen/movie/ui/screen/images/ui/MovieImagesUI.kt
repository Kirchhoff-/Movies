package com.kirchhoff.movies.screen.movie.ui.screen.images.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.ui.screen.images.model.MovieImagesScreenState

@SuppressWarnings("MagicNumber")
@Composable
internal fun MovieImagesUI(
    screenState: MovieImagesScreenState,
    onImageClick: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        MoviesToolbar(
            title = stringResource(
                id = R.string.movie_images_format,
                screenState.title
            )
        ) { onBackPressed.invoke() }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                count = screenState.image.size,
                itemContent = {
                    MovieImagesItemUI(
                        screenState.image[it],
                        onImageClick
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun MovieImagesUIPreview() {
    MovieImagesUI(
        MovieImagesScreenState(
            title = "Title",
            image = emptyList()
        ),
        onImageClick = {},
        onBackPressed = {}
    )
}
