package com.kirchhoff.movies.screen.tvshow.ui.screen.details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.screen.tvshow.data.UITvShowDetails
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.model.TvShowDetailsScreenState

@Composable
internal fun TvShowDetailsUI(
    screenState: TvShowDetailsScreenState,
    onBackPressed: () -> Unit
) {
    Column {
        MoviesToolbar(
            title = screenState.title,
            onBackPressed = { onBackPressed.invoke() }
        )

        when {
            screenState.isLoading -> ShowLoading()
            screenState.errorMessage.isNotEmpty() -> ShowError()
            else -> ShowUI()
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
private fun ShowUI() {

}

@Preview
@Composable
private fun TvShowDetailsUIPreview() {
    TvShowDetailsUI(
        screenState = TvShowDetailsScreenState(
            title = "",
            details = UITvShowDetails(
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
            errorMessage = ""
        ),
        onBackPressed = {}
    )
}
