package com.kirchhoff.movies.screen.tvshow.ui.screen.list.ui

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.ui.compose.ListScrollHandler
import com.kirchhoff.movies.screen.tvshow.R
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.model.TvShowListScreenState
import com.kirchhoff.movies.screen.tvshow.ui.view.TvShowItemUI

@Composable
internal fun TvShowListUI(
    screenState: TvShowListScreenState,
    onLoadMore: () -> Unit,
    onTvShowClick: (UITv) -> Unit
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
    var errorMessage by rememberSaveable { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = screenState.tvShowListVisible) {
            LazyColumn(state = listState) {
                items(
                    count = screenState.tvShowList.size,
                    itemContent = {
                        TvShowItemUI(
                            tvShow = screenState.tvShowList[it],
                            onTvShowClick = onTvShowClick
                        )
                    }
                )
                item {
                    AnimatedVisibility(visible = screenState.paginationVisible) {
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    }
                }
            }
            ListScrollHandler(listState = listState) {
                onLoadMore()
            }
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = screenState.loadingVisible
        ) {
            CircularProgressIndicator()
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = screenState.emptyTextVisible
        ) {
            Text(
                text = stringResource(R.string.empty_tw_shows),
                color = colorResource(com.kirchhoff.movies.core.R.color.text_main)
            )
        }
    }

    if (screenState.errorMessage.isNotEmpty() && errorMessage.isEmpty()) {
        errorMessage = screenState.errorMessage
        SideEffect {
            Toast.makeText(context, screenState.errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}

@Preview
@Composable
private fun TvShowListUIPreview() {
    TvShowListUI(
        screenState = TvShowListScreenState(
            tvShowList = emptyList(),
            errorMessage = "",
            loadingVisible = false,
            paginationVisible = false,
            tvShowListVisible = false,
            emptyTextVisible = false
        ),
        onLoadMore = {},
        onTvShowClick = {}
    )
}
