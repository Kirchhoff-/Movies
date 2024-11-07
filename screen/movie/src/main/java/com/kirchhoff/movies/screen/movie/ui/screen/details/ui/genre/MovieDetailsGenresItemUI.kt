@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.movie.ui.screen.details.ui.genre

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.core.data.UIGenre
import com.kirchhoff.movies.core.ui.resources.Colors

@Composable
internal fun MovieDetailsGenresItemUI(
    genre: UIGenre,
    onGenreClick: (UIGenre) -> Unit
) {
    Text(
        modifier = Modifier
            .padding(2.dp)
            .background(Colors.White)
            .border(
                width = 1.dp,
                color = Colors.Black,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = { onGenreClick.invoke(genre) }
            )
            .padding(8.dp),
        color = Colors.Black,
        fontSize = 14.sp,
        text = genre.name
    )
}

@Preview
@Composable
private fun MovieDetailsGenresItemUIPreview() {
    MovieDetailsGenresItemUI(
        genre = UIGenre.Default,
        onGenreClick = {}
    )
}
