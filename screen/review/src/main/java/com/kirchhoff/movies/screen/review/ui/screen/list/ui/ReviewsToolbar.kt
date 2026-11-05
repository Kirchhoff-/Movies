package com.kirchhoff.movies.screen.review.ui.screen.list.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.screen.review.R
import com.kirchhoff.movies.screen.review.ui.screen.model.ReviewScreenState
import com.kirchhoff.movies.screen.review.ui.transition.ReviewsTransitionScope

@Composable
internal fun ReviewsToolbar(
    title: String,
    subtitle: String,
    details: ReviewScreenState.ContentState.Details?,
    onOpenInBrowserClick: (String) -> Unit,
    onShareReviewClick: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    val showReviewCount = details == null && subtitle.isNotEmpty()

    TopAppBar(
        title = {
            Column {
                Text(
                    text = title,
                    color = Colors.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                AnimatedVisibility(
                    visible = showReviewCount,
                    enter = ReviewsTransitionScope.toolbarSubtitleEnter,
                    exit = ReviewsTransitionScope.toolbarSubtitleExit
                ) {
                    Text(
                        text = subtitle,
                        color = Colors.White,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        },
        actions = {
            AnimatedContent(
                targetState = details,
                transitionSpec = { ReviewsTransitionScope.toolbarActionsTransform },
                contentAlignment = Alignment.CenterEnd,
                contentKey = { it != null },
                label = "Review toolbar actions transition"
            ) { reviewDetails ->
                Row {
                    if (reviewDetails != null) {
                        val shareText = reviewDetails.shareText.asString(LocalContext.current)
                        IconButton(onClick = { onShareReviewClick(shareText) }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_share),
                                tint = Colors.White,
                                contentDescription = stringResource(R.string.review_share)
                            )
                        }
                        IconButton(onClick = { onOpenInBrowserClick(reviewDetails.review.url) }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_open_in_new),
                                tint = Colors.White,
                                contentDescription = stringResource(R.string.review_open_original)
                            )
                        }
                    }
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = Colors.White,
                    contentDescription = ""
                )
            }
        },
        backgroundColor = Colors.Primary
    )
}
