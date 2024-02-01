@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.tvshow.ui.screen.details.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.tvshow.data.UITvShowInfo
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.model.TvShowDetailsScreenState
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.ui.credits.TvShowDetailsCreditsUI
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.ui.info.TvShowDetailsInfoUI
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.ui.keywords.TvShowDetailsKeywordsUI

@ExperimentalLayoutApi
@Composable
internal fun TvShowDetailsUI(
    screenState: TvShowDetailsScreenState,
    onCreditItemClick: (UIEntertainmentPerson) -> Unit,
    onReviewsClick: () -> Unit,
    onBackPressed: () -> Unit
) {
    Column {
        MoviesToolbar(
            title = screenState.title.asString(LocalContext.current),
            onBackPressed = { onBackPressed.invoke() }
        )

        when {
            screenState.isLoading -> ShowLoading()
            screenState.errorMessage.asString(LocalContext.current).isNotEmpty() -> ShowError(screenState)
            else -> ShowUI(
                screenState = screenState,
                onCreditItemClick = onCreditItemClick,
                onReviewsClick = onReviewsClick
            )
        }
    }
}

@Composable
private fun ShowLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ShowError(screenState: TvShowDetailsScreenState) {
    val context = LocalContext.current
    val error = screenState.errorMessage.asString(context)
    var errorMessage by rememberSaveable { mutableStateOf("") }

    if (error.isNotEmpty() && errorMessage.isEmpty()) {
        errorMessage = error
        SideEffect {
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }
}

@ExperimentalLayoutApi
@SuppressWarnings("LongMethod")
@Composable
private fun ShowUI(
    screenState: TvShowDetailsScreenState,
    onCreditItemClick: (UIEntertainmentPerson) -> Unit,
    onReviewsClick: () -> Unit
) {
    val creditsVisible = screenState.credits.cast?.isNotEmpty() == true || screenState.credits.crew?.isNotEmpty() == true

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
        Spacer(modifier = Modifier.height(8.dp))
        TvShowDetailsKeywordsUI(keywords = screenState.info.genres.map { it.name })
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = screenState.info.overview,
            color = colorResource(R.color.text_hint),
            fontSize = 14.sp
        )
        if (creditsVisible) {
            Spacer(modifier = Modifier.height(16.dp))
            TvShowDetailsCreditsUI(
                credits = screenState.credits,
                onItemClick = onCreditItemClick
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = { onReviewsClick.invoke() }
                )
                .padding(16.dp),
            text = StringValue
                .IdText(com.kirchhoff.movies.screen.tvshow.R.string.tv_show_reviews)
                .asString(LocalContext.current),
            color = colorResource(R.color.text_main),
            fontSize = 18.sp
        )
    }
}

@ExperimentalLayoutApi
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
        onCreditItemClick = {},
        onReviewsClick = {},
        onBackPressed = {}
    )
}
