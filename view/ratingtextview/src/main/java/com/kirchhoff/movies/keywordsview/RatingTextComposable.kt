package com.kirchhoff.movies.keywordsview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.ratingtextview.R
import java.text.DecimalFormat

private val ratingFormat = DecimalFormat("#.0")

@SuppressWarnings("MagicNumber")
@Composable
fun RatingText(
    modifier: Modifier,
    voteAverage: Float?
) {
    Text(
        modifier = modifier
            .background(
                color = colorResource(R.color.rating_text_view_background),
                shape = RoundedCornerShape(
                    bottomStart = 8.dp,
                    topEnd = 8.dp
                )
            )
            .padding(8.dp),
        color = colorResource(R.color.rating_text_view_text_color),
        fontSize = 14.sp,
        text = ratingFormat.format(voteAverage)
    )
}
