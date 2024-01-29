@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.tvshow.ui.screen.details.ui.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.tvshow.R
import com.kirchhoff.movies.screen.tvshow.data.UITvShowInfo
import com.kirchhoff.movies.voteview.VoteViewComposable

@Composable
internal fun TvShowDetailsInfoUI(
    info: UITvShowInfo,
    posterPath: String?
) {
    val context = LocalContext.current

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
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            VoteViewComposable(
                voteAverage = info.voteAverage,
                voteCount = info.voteCount
            )
            Text(
                style = infoTextStyle,
                text = StringValue.IdText(
                    R.string.tv_seasons_format,
                    info.numberOfSeasons
                ).asString(context)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                style = infoTextStyle,
                text = StringValue.IdText(
                    R.string.tv_episodes_format,
                    info.numberOfEpisodes
                ).asString(context)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                style = infoTextStyle,
                text = StringValue.IdText(
                    R.string.tv_first_air_date_format,
                    info.firstAirDate
                ).asString(context)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                style = infoTextStyle,
                text = StringValue.IdText(
                    R.string.tv_status_format,
                    info.status
                ).asString(context)
            )
        }
    }
}

private val infoTextStyle: TextStyle = TextStyle(
    fontSize = 14.sp,
    color = Color.Black
)

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
