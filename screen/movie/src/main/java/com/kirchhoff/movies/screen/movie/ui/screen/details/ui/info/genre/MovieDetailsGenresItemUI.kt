@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.movie.ui.screen.details.ui.info.genre

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
import com.kirchhoff.movies.core.data.UIGenre

@Composable
internal fun MovieDetailsGenresItemUI(genre: UIGenre) {
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
        text = genre.name
    )
}

@Preview
@Composable
private fun MovieDetailsGenresItemUIPreview() {
    MovieDetailsGenresItemUI(
        UIGenre(
            id = "",
            name = ""
        )
    )
}
