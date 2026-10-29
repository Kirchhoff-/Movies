package com.kirchhoff.movies.screen.review.ui.screen.list.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.screen.review.R
import com.kirchhoff.movies.screen.review.data.ReviewUIInfo
import com.kirchhoff.movies.screen.review.ui.transition.ReviewsTransitionScope

@SuppressWarnings("MagicNumber", "LongMethod")
@Composable
internal fun ReviewAuthorUI(
    author: ReviewUIInfo.Author,
    transition: ReviewsTransitionScope
) {
    val authorName: String = author.displayName ?: stringResource(R.string.review_author)
    val hasUsername: Boolean = author.username != null
    val rating: Int? = author.rating
    val hasRating: Boolean = rating != null

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = author.avatar,
            modifier = transition.avatarModifier()
                .size(48.dp)
                .clip(CircleShape),
            placeholder = painterResource(com.kirchhoff.movies.core.R.drawable.ic_account_circle),
            error = painterResource(com.kirchhoff.movies.core.R.drawable.ic_account_circle),
            fallback = painterResource(com.kirchhoff.movies.core.R.drawable.ic_account_circle),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                modifier = transition.authorModifier(),
                text = authorName,
                color = Colors.TextMain,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            if (hasUsername) {
                Text(
                    text = stringResource(R.string.review_author_username_format, author.username),
                    color = Colors.TextHint,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        if (hasRating) {
            Spacer(Modifier.width(12.dp))
            ReviewsRatingView(
                rating = rating,
                modifier = transition.ratingModifier()
            )
        }
    }
}
