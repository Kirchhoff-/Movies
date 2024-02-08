@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.tvshow.ui.screen.details.ui.keywords

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun TvShowDetailsKeywordsItemUI(text: String) {
    Text(
        modifier = Modifier
            .padding(2.dp)
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        color = Color.Black,
        fontSize = 14.sp,
        text = text
    )
}

@Preview
@Composable
private fun TvShowDetailsKeywordsItemUIPreview() {
    TvShowDetailsKeywordsItemUI("Keyword")
}
