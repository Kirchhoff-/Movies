package com.kirchhoff.movies.screen.review.ui.screen.list.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.linkifytext.LinkifyText
import com.kirchhoff.movies.screen.review.data.ReviewUIInfo
import com.kirchhoff.movies.screen.review.ui.transition.ReviewsTransitionScope

@SuppressWarnings("MagicNumber")
@Composable
internal fun ReviewsDetailsUI(
    review: ReviewUIInfo,
    transition: ReviewsTransitionScope,
    onAuthorClick: () -> Unit
) {
    val textPadding = 16.dp
    val textSize = 14.sp
    val scroll = rememberScrollState(0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.White)
            .verticalScroll(scroll)
            .padding(
                top = textPadding,
                start = textPadding,
                end = textPadding
            )
    ) {
        ReviewAuthorUI(
            author = review.author,
            transition = transition,
            onAuthorClick = onAuthorClick
        )
        LinkifyText(
            modifier = transition.contentModifier().fillMaxWidth(),
            text = review.content,
            color = Colors.TextHint,
            fontSize = textSize
        )
    }
}
