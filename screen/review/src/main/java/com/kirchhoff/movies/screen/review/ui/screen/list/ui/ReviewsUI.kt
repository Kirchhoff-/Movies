package com.kirchhoff.movies.screen.review.ui.screen.list.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.screen.review.R
import com.kirchhoff.movies.screen.review.data.ReviewUIInfo
import com.kirchhoff.movies.screen.review.ui.screen.list.ui.details.ReviewsAuthorBottomSheet
import com.kirchhoff.movies.screen.review.ui.screen.list.ui.details.ReviewsDetailsUI
import com.kirchhoff.movies.screen.review.ui.screen.list.ui.list.ReviewListUI
import com.kirchhoff.movies.screen.review.ui.screen.model.ReviewScreenState
import com.kirchhoff.movies.screen.review.ui.transition.ReviewsTransitionScope

@SuppressWarnings("LongParameterList")
@Composable
internal fun ReviewsUI(
    screenState: ReviewScreenState,
    onLoadMore: () -> Unit,
    onReviewClick: (ReviewUIInfo) -> Unit,
    onCloseDetails: () -> Unit,
    onOpenInBrowserClick: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    var selectedAuthor by rememberSaveable { mutableStateOf<ReviewUIInfo.Author?>(null) }
    val listState = rememberLazyListState()
    val stateHolder = rememberSaveableStateHolder()
    val selectedReview = (screenState.contentState as? ReviewScreenState.ContentState.Details)?.review
    val showDetails = selectedReview != null
    val handleDetailsBack = showDetails && selectedAuthor == null
    val toolbarTitle = screenState.title.asString(LocalContext.current)
    val onNavigateBack = if (showDetails) onCloseDetails else onBackPressed

    BackHandler(
        enabled = handleDetailsBack,
        onBack = onCloseDetails
    )

    ReviewsAuthorBottomSheet(
        author = selectedAuthor,
        onDismiss = { selectedAuthor = null },
        onOpenProfileClick = onOpenInBrowserClick
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            MoviesToolbar(
                title = toolbarTitle,
                actions = {
                    if (selectedReview != null) {
                        IconButton(onClick = { onOpenInBrowserClick(selectedReview.url) }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_open_in_browser),
                                tint = Colors.White,
                                contentDescription = ""
                            )
                        }
                    }
                },
                onBackPressed = onNavigateBack
            )
            SharedTransitionLayout(modifier = Modifier.weight(1f)) {
                AnimatedContent(
                    targetState = selectedReview,
                    modifier = Modifier.fillMaxSize(),
                    transitionSpec = { ReviewsTransitionScope.contentTransform },
                    contentAlignment = Alignment.TopStart,
                    label = "Review details transition"
                ) { review ->
                    val reviewKey = review?.url.orEmpty()
                    val contentKey = review?.url ?: "reviews-list"
                    val transition = ReviewsTransitionScope(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@AnimatedContent,
                        reviewKey = reviewKey
                    )
                    stateHolder.SaveableStateProvider(key = contentKey) {
                        if (review == null) {
                            ReviewListUI(
                                screenState = screenState,
                                listState = listState,
                                transition = transition,
                                onLoadMore = onLoadMore,
                                onReviewClick = onReviewClick,
                                onAuthorClick = { selectedAuthor = it }
                            )
                        } else {
                            ReviewsDetailsUI(
                                review = review,
                                transition = transition,
                                onAuthorClick = { selectedAuthor = review.author }
                            )
                        }
                    }
                }
            }
        }
    }
}
