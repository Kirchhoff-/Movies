@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.tvshow.ui.screen.details.ui.info

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.screen.tvshow.data.UITvShowInfo

@Composable
internal fun TvShowDetailsInfoUI(
    info: UITvShowInfo,
    posterPath: String?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .height(175.dp)
                .width(120.dp),
            model = BASE_POSTER_PATH + posterPath,
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
    }
}

@Preview
@Composable
private fun TvShowDetailInfoUIPreview() {
    TvShowDetailsInfoUI(
        info = UITvShowInfo(
            numberOfEpisodes = 0,
            numberOfSeasons = 0,
            overview = "",
            status = "",
            firstAirDate = "",
            voteCount = null,
            voteAverage = null,
            genres = emptyList()
        ),
        posterPath = ""
    )
}
