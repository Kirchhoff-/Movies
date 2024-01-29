package com.kirchhoff.movies.screen.tvshow.ui.screen.details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.tvshow.data.UITvShowInfo
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.model.TvShowDetailsScreenState
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.ui.info.TvShowDetailsInfoUI

@Composable
internal fun TvShowDetailsUI(
    screenState: TvShowDetailsScreenState,
    onBackPressed: () -> Unit
) {
    Column {
        MoviesToolbar(
            title = screenState.title.asString(LocalContext.current),
            onBackPressed = { onBackPressed.invoke() }
        )

        when {
            screenState.isLoading -> ShowLoading()
            screenState.errorMessage.asString(LocalContext.current).isNotEmpty() -> ShowError()
            else -> ShowUI(screenState)
        }
    }
}

@Composable
private fun ShowLoading() {

}

@Composable
private fun ShowError() {

}

@Composable
private fun ShowUI(
    screenState: TvShowDetailsScreenState
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            model = BASE_POSTER_PATH + screenState.backdropPath,
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp),
            text = screenState.title.asString(LocalContext.current),
            color = colorResource(R.color.text_main),
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        TvShowDetailsInfoUI(
            info = screenState.info,
            posterPath = screenState.posterPath
        )
    }
}

@Preview
@Composable
private fun TvShowDetailsUIPreview() {
    TvShowDetailsUI(
        screenState = TvShowDetailsScreenState(
            title = StringValue.SimpleText("TvShow"),
            backdropPath = "",
            posterPath = "",
            info = UITvShowInfo(
                numberOfSeasons = 0,
                numberOfEpisodes = 0,
                overview = "",
                status = "",
                firstAirDate = "",
                voteCount = null,
                voteAverage = null,
                genres = emptyList()
            ),
            credits = UIEntertainmentCredits(
                cast = emptyList(),
                crew = emptyList()
            ),
            isLoading = false,
            errorMessage = StringValue.Empty
        ),
        onBackPressed = {}
    )
}
