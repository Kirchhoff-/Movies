package com.kirchhoff.movies.screen.review.ui.screen.list.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kirchhoff.movies.screen.review.data.ReviewUIInfo
import com.kirchhoff.movies.screen.review.ui.screen.list.model.ReviewsListScreenState
import com.kirchhoff.movies.screen.review.ui.screen.list.ui.details.ReviewsAuthorBottomSheet
import com.kirchhoff.movies.screen.review.ui.screen.list.ui.details.ReviewsDetailsUI
import com.kirchhoff.movies.screen.review.ui.screen.list.ui.list.ReviewListUI
import com.kirchhoff.movies.screen.review.ui.transition.ReviewsTransitionScope

@Composable
internal fun ReviewsUI(
    screenState: ReviewsListScreenState,
    onLoadMore: () -> Unit,
    onOpenInBrowserClick: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    var selectedReview by rememberSaveable { mutableStateOf<ReviewUIInfo?>(null) }
    var selectedAuthor by rememberSaveable { mutableStateOf<ReviewUIInfo.Author?>(null) }
    val listState = rememberLazyListState()
    val stateHolder = rememberSaveableStateHolder()

    BackHandler(enabled = selectedReview != null && selectedAuthor == null) {
        selectedReview = null
    }

    ReviewsAuthorBottomSheet(
        author = selectedAuthor,
        onDismiss = { selectedAuthor = null },
        onOpenProfileClick = onOpenInBrowserClick
    ) {
        SharedTransitionLayout(modifier = Modifier.fillMaxSize()) {
            AnimatedContent(
                targetState = selectedReview,
                modifier = Modifier.fillMaxSize(),
                transitionSpec = { ReviewsTransitionScope.contentTransform },
                contentAlignment = Alignment.TopStart,
                label = "Review details transition"
            ) { review ->
                val transition = ReviewsTransitionScope(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@AnimatedContent,
                    reviewKey = review?.url.orEmpty()
                )
                stateHolder.SaveableStateProvider(key = review?.url ?: "reviews-list") {
                    if (review == null) {
                        ReviewListUI(
                            screenState = screenState,
                            listState = listState,
                            transition = transition,
                            onLoadMore = onLoadMore,
                            onReviewClick = { selected, _ -> selectedReview = selected },
                            onAuthorClick = { selectedAuthor = it },
                            onBackPressed = onBackPressed
                        )
                    } else {
                        ReviewsDetailsUI(
                            title = screenState.title,
                            review = review,
                            transition = transition,
                            onOpenInBrowserClick = onOpenInBrowserClick,
                            onAuthorClick = { selectedAuthor = review.author },
                            onBackPressed = { selectedReview = null }
                        )
                    }
                }
            }
        }
    }
}
