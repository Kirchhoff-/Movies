package com.kirchhoff.movies.screen.review.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.screen.review.R
import com.kirchhoff.movies.screen.review.data.UIReview

@SuppressWarnings("MagicNumber")
@Composable
fun ReviewDetailsUI(
    title: String,
    review: UIReview,
    onBackClicked: () -> Unit
) {
    val textPadding = 16.dp
    val textSize = 14.sp
    val scroll = rememberScrollState(0)

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(
                        id = R.string.review_details_title_format,
                        review.author,
                        title
                    ),
                    color = colorResource(R.color.white)
                )
            },
            navigationIcon = {
                IconButton(onClick = { onBackClicked.invoke() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = colorResource(R.color.white),
                        contentDescription = ""
                    )
                }
            },
            backgroundColor = colorResource(R.color.colorPrimary)
        )
        Text(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.background))
                .padding(
                    top = textPadding,
                    start = textPadding,
                    end = textPadding
                )
                .verticalScroll(scroll),
            text = review.content,
            color = colorResource(R.color.text_hint),
            fontSize = textSize
        )
    }
}
