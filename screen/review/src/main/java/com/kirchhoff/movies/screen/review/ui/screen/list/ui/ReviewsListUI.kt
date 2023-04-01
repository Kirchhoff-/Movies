package com.kirchhoff.movies.screen.review.ui.screen.list.ui

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
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.screen.review.R
import com.kirchhoff.movies.screen.review.data.UIReview
import com.kirchhoff.movies.screen.review.ui.screen.list.model.ReviewsListScreenState

@Composable
internal fun ReviewListUI(
    screenState: ReviewsListScreenState,
    onLoadMore: () -> Unit,
    onReviewClick: (UIReview) -> Unit,
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
    var errorMessage by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        MoviesToolbar(title = stringResource(R.string.review_list_title_format, screenState.title)) {
            onBackPressed.invoke()
        }

        Box(modifier = Modifier.fillMaxSize()) {
            this@Column.AnimatedVisibility(visible = screenState.reviewsVisible) {
                LazyColumn(state = listState) {
                    items(
                        count = screenState.reviewsList.size,
                        itemContent = { ReviewItem(screenState.reviewsList[it], onReviewClick) }
                    )
                    item {
                        this@Column.AnimatedVisibility(visible = screenState.paginationVisible) {
                            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
                ReviewsListScrollHandler(listState = listState) {
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
                    text = stringResource(R.string.empty_reviews),
                    color = colorResource(R.color.text_main)
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
internal fun ReviewListPreview() {
    ReviewListUI(
        screenState = ReviewsListScreenState(
            title = "Some movie title",
            reviewsList = listOf(
                UIReview(
                    author = "Name 1",
                    content = "Content 1",
                    authorAvatar = null,
                    rating = null
                ),
                UIReview(
                    author = "Name 1",
                    content = "Content 1",
                    authorAvatar = null,
                    rating = null
                )
            ),
            errorMessage = "Some error message",
            reviewsVisible = true,
            loadingVisible = true,
            paginationVisible = true,
            emptyTextVisible = false
        ),
        onLoadMore = {},
        onReviewClick = { UIReview("", "", "", null) },
        onBackPressed = {}
    )
}
