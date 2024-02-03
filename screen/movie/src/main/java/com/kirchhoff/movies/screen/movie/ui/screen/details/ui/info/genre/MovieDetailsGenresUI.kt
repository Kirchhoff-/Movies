@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.movie.ui.screen.details.ui.info.genre

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirchhoff.movies.core.data.UIGenre

@ExperimentalLayoutApi
@Composable
internal fun MovieDetailsGenresUI(genres: List<UIGenre>) {
    FlowRow(modifier = Modifier.padding(start = 12.dp)) {
        genres.forEach { genre ->
            MovieDetailsGenresItemUI(genre = genre)
        }
    }
}

@ExperimentalLayoutApi
@Preview
@Composable
private fun MovieDetailsGenresUIPreview() {
    MovieDetailsGenresUI(genres = emptyList())
}
