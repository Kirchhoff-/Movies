@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.movie.ui.screen.details.ui.info.trailers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.data.UITrailer

@Composable
internal fun MovieDetailsTrailersUI(
    trailers: List<UITrailer>,
    onTrailerClick: (UITrailer) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                ),
            text = stringResource(R.string.movie_trailers),
            color = Colors.Black,
            fontSize = 20.sp
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(
                count = trailers.size,
                itemContent = {
                    MovieDetailsTrailersItemUI(
                        trailer = trailers[it],
                        onTrailerClick = onTrailerClick
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun MovieDetailsTrailersUIPreview() {
    MovieDetailsTrailersUI(
        trailers = emptyList(),
        onTrailerClick = {}
    )
}
