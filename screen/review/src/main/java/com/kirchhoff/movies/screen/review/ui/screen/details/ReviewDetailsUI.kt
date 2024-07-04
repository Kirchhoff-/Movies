package com.kirchhoff.movies.screen.review.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.linkifytext.LinkifyText
import com.kirchhoff.movies.screen.review.R
import com.kirchhoff.movies.screen.review.data.UIReview

@SuppressWarnings("MagicNumber")
@Composable
internal fun ReviewDetailsUI(
    title: String,
    review: UIReview,
    onBackPressed: () -> Unit
) {
    val textPadding = 16.dp
    val textSize = 14.sp
    val scroll = rememberScrollState(0)

    Column(modifier = Modifier.fillMaxSize()) {
        MoviesToolbar(
            title = stringResource(
                id = R.string.review_details_title_format,
                review.author,
                title
            )
        ) { onBackPressed.invoke() }

        LinkifyText(
            modifier = Modifier
                .fillMaxSize()
                .background(Colors.White)
                .padding(
                    top = textPadding,
                    start = textPadding,
                    end = textPadding
                )
                .verticalScroll(scroll),
            text = review.content,
            color = Colors.TextHint,
            fontSize = textSize
        )
    }
}
