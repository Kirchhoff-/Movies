package com.kirchhoff.movies.screen.review.ui.screen.list.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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
import com.kirchhoff.movies.screen.review.ui.screen.list.ui.details.ReviewsDetailsUI
import com.kirchhoff.movies.screen.review.ui.screen.list.ui.list.ReviewListUI

@Composable
internal fun ReviewsUI(
    screenState: ReviewsListScreenState,
    onLoadMore: () -> Unit,
    onOpenInBrowserClick: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    var selectedReview by rememberSaveable { mutableStateOf<ReviewUIInfo?>(null) }
    val listState = rememberLazyListState()
    val stateHolder = rememberSaveableStateHolder()

    BackHandler(enabled = selectedReview != null) {
        selectedReview = null
    }

    SharedTransitionLayout(modifier = Modifier.fillMaxSize()) {
        AnimatedContent(
            targetState = selectedReview,
            modifier = Modifier.fillMaxSize(),
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            contentAlignment = Alignment.TopStart,
            label = "Review details transition"
        ) { review ->
            stateHolder.SaveableStateProvider(key = review?.url ?: "reviews-list") {
                if (review == null) {
                    ReviewListUI(
                        screenState = screenState,
                        listState = listState,
                        reviewContentModifier = { item ->
                            Modifier.sharedBounds(
                                sharedContentState = rememberSharedContentState(key = item.url),
                                animatedVisibilityScope = this@AnimatedContent,
                                resizeMode = SharedTransitionScope.ResizeMode.scaleToBounds(
                                    alignment = Alignment.TopStart
                                )
                            )
                        },
                        onLoadMore = onLoadMore,
                        onReviewClick = { selected, _ -> selectedReview = selected },
                        onBackPressed = onBackPressed
                    )
                } else {
                    ReviewsDetailsUI(
                        title = screenState.title,
                        review = review,
                        modifier = Modifier.sharedBounds(
                            sharedContentState = rememberSharedContentState(key = review.url),
                            animatedVisibilityScope = this@AnimatedContent,
                            resizeMode = SharedTransitionScope.ResizeMode.scaleToBounds(
                                alignment = Alignment.TopStart
                            )
                        ),
                        onOpenInBrowserClick = onOpenInBrowserClick,
                        onBackPressed = { selectedReview = null }
                    )
                }
            }
        }
    }
}
