package com.kirchhoff.movies.screen.review.ui.screen.list.ui.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.screen.review.R
import com.kirchhoff.movies.screen.review.data.ReviewUIInfo
import com.kirchhoff.movies.screen.review.ui.screen.list.ui.view.ReviewsRatingView
import com.kirchhoff.movies.screen.review.ui.transition.ReviewsTransitionScope

@SuppressWarnings("MagicNumber", "LongMethod")
@Composable
internal fun ReviewItem(
    review: ReviewUIInfo,
    transition: ReviewsTransitionScope,
    onAuthorClick: (ReviewUIInfo.Author) -> Unit,
    onReviewClick: (ReviewUIInfo) -> Unit
) {
    val authorName = review.author.getName() ?: stringResource(R.string.review_author)
    var showReadMore by remember(review.url, review.content) { mutableStateOf(false) }
    val rating = review.author.rating
    val showRating = rating != null
    val showBottomRow = showRating || showReadMore

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true),
                onClick = { onReviewClick.invoke(review) }
            ),
        elevation = 2.dp,
        backgroundColor = Colors.White,
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = transition.avatarModifier()
                        .height(32.dp)
                        .width(32.dp)
                        .clip(CircleShape)
                        .clickable { onAuthorClick(review.author) },
                    model = review.author.avatar,
                    placeholder = painterResource(com.kirchhoff.movies.core.R.drawable.ic_account_circle),
                    error = painterResource(com.kirchhoff.movies.core.R.drawable.ic_account_circle),
                    fallback = painterResource(com.kirchhoff.movies.core.R.drawable.ic_account_circle),
                    contentDescription = authorName
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    modifier = transition.authorModifier(),
                    text = review.author.label,
                    color = Colors.TextMain,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                modifier = transition.contentModifier(),
                text = review.content,
                color = Colors.TextHint,
                fontSize = 14.sp,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { showReadMore = it.hasVisualOverflow }
            )
            if (showBottomRow) {
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (showRating) {
                        ReviewsRatingView(
                            rating = rating,
                            modifier = transition.ratingModifier()
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    if (showReadMore) {
                        Text(
                            text = stringResource(R.string.review_read_more),
                            modifier = Modifier
                                .clickable { onReviewClick(review) }
                                .padding(8.dp),
                            color = Colors.Primary,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ReviewItemPreview() {
    SharedTransitionLayout {
        AnimatedVisibility(visible = true) {
            ReviewItem(
                review = ReviewUIInfo.Default,
                transition = ReviewsTransitionScope(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@AnimatedVisibility,
                    reviewKey = ReviewUIInfo.Default.url
                ),
                onAuthorClick = {},
                onReviewClick = {}
            )
        }
    }
}
