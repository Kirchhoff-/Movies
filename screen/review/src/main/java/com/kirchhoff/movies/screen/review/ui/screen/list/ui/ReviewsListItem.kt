package com.kirchhoff.movies.screen.review.ui.screen.list.ui

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
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.screen.review.R
import com.kirchhoff.movies.screen.review.data.UIReview

@SuppressWarnings("MagicNumber", "LongMethod")
@Composable
internal fun ReviewItem(
    review: UIReview,
    onReviewClick: (UIReview) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
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
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp)
                        .clip(CircleShape),
                    model = BASE_POSTER_PATH + review.authorAvatar,
                    placeholder = painterResource(com.kirchhoff.movies.core.R.drawable.ic_account_circle),
                    error = painterResource(com.kirchhoff.movies.core.R.drawable.ic_account_circle),
                    contentDescription = ""
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = review.author,
                    color = Colors.TextMain,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = review.content,
                color = Colors.TextHint,
                fontSize = 14.sp,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(8.dp))
            if (review.rating != null) {
                Text(
                    stringResource(
                        R.string.review_rating_format,
                        review.rating
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun ReviewItemPreview() {
    ReviewItem(
        review = UIReview.Default,
        onReviewClick = {}
    )
}
