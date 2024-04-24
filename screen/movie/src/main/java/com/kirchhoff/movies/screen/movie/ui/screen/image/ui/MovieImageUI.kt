package com.kirchhoff.movies.screen.movie.ui.screen.image.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar

@Composable
internal fun MovieImageUI(
    imagePath: String,
    onBackPressed: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        MoviesToolbar(title = "") { onBackPressed.invoke() }

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = BASE_POSTER_PATH + imagePath,
            contentDescription = ""
        )
    }
}

@Preview
@Composable
private fun PersonImagesUIPreview() {
    MovieImageUI("") {}
}
