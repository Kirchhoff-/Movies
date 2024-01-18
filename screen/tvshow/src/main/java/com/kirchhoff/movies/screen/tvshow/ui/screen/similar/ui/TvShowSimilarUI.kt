package com.kirchhoff.movies.screen.tvshow.ui.screen.similar.ui

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.tvshow.R
import com.kirchhoff.movies.screen.tvshow.ui.screen.similar.model.TvShowSimilarScreenState

@Composable
internal fun TvShowSimilarUI(
    screenState: TvShowSimilarScreenState,
    onLoadMore: () -> Unit,
    onTvShowClick: (UITv) -> Unit,
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
    var errorMessage by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        MoviesToolbar(
            title = screenState.title.asString(context),
            onBackPressed = { onBackPressed.invoke() }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            this@Column.AnimatedVisibility(visible = screenState.tvShowListVisible) {
                LazyColumn(state = listState) {
                    items(
                        count = screenState.tvShowList.size,
                        itemContent = {
                            TvShowSimilarItemUI(
                                tvShow = screenState.tvShowList[it],
                                onTvShowClick = onTvShowClick
                            )
                        }
                    )
                    item {
                        this@Column.AnimatedVisibility(visible = screenState.paginationVisible) {
                            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
                ListScrollHandler(listState = listState) {
                    onLoadMore()
                }
            }
            this@Column.AnimatedVisibility(
                modifier = Modifier.align(Alignment.Center),
                visible = screenState.loadingVisible
            ) {
                CircularProgressIndicator()
            }
            this@Column.AnimatedVisibility(
                modifier = Modifier.align(Alignment.Center),
                visible = screenState.emptyTextVisible
            ) {
                Text(
                    text = stringResource(R.string.tv_show_empty_similar),
                    color = colorResource(com.kirchhoff.movies.core.R.color.text_main)
                )
            }
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
    TvShowSimilarUI(
        screenState = TvShowSimilarScreenState(
            tvShowList = emptyList(),
            title = StringValue.SimpleText("Title"),
            errorMessage = "",
            loadingVisible = false,
            paginationVisible = false,
            tvShowListVisible = false,
            emptyTextVisible = false
        ),
        onLoadMore = {},
        onTvShowClick = {},
        onBackPressed = {}
    )
}
