@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.movie.ui.view.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.movie.R

@Composable
internal fun MovieSectionUI(
    movies: List<UIMovie>,
    title: StringValue,
    additionalTopMargin: Dp = 0.dp,
    onItemClick: (UIMovie) -> Unit,
    onSeeAllClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if (additionalTopMargin != 0.dp) {
            Spacer(Modifier.height(additionalTopMargin))
        }
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = title.asString(LocalContext.current),
                color = colorResource(com.kirchhoff.movies.core.R.color.text_main),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = { onSeeAllClick.invoke() }
                ),
                text = stringResource(com.kirchhoff.movies.creditsview.R.string.see_all),
                color = colorResource(com.kirchhoff.movies.core.R.color.link_color),
                fontSize = 16.sp
            )
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(
                count = movies.size,
                itemContent = {
                    MovieSectionItemUI(
                        movie = movies[it],
                        onItemClick = onItemClick
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun MovieDetailsSimilarUIPreview() {
    MovieSectionUI(
        movies = emptyList(),
        title = StringValue.IdText(R.string.movie_similar_movies),
        onItemClick = {},
        onSeeAllClick = {}
    )
}
