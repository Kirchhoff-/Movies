package com.kirchhoff.movies.screen.review.ui.screen.list.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.screen.review.R

@SuppressWarnings("MagicNumber")
@Composable
internal fun ReviewsRatingView(
    rating: Int,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .background(
                color = Colors.Primary.copy(alpha = 0.12f),
                shape = CircleShape
            )
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(com.kirchhoff.movies.core.R.drawable.ic_star),
            contentDescription = null,
            tint = Colors.Primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = stringResource(R.string.review_author_rating_format, rating),
            color = Colors.Primary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1
        )
    }
}
