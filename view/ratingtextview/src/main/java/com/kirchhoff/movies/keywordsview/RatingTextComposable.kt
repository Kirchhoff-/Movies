@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.keywordsview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat

private val ratingFormat = DecimalFormat("#.0")

private val backgroundColor = Color(0x66000000)

private val textColor = Color(0xFFFFFFFF)

@SuppressWarnings("MagicNumber")
@Composable
fun RatingText(
    modifier: Modifier,
    voteAverage: Float?,
    fontSize: TextUnit = 14.sp
) {
    Text(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(
                    bottomStart = 8.dp,
                    topEnd = 8.dp
                )
            )
            .padding(8.dp),
        color = textColor,
        fontSize = fontSize,
        text = ratingFormat.format(voteAverage)
    )
}
