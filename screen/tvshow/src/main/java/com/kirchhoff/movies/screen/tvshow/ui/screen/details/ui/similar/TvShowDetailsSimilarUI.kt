@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.tvshow.ui.screen.details.ui.similar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.core.data.UITv

@Composable
internal fun TvShowDetailsSimilarUI(
    tvShows: List<UITv>,
    onItemClick: (UITv) -> Unit,
    onSeeAllClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = stringResource(com.kirchhoff.movies.screen.tvshow.R.string.similar_tv_shows),
                color = colorResource(R.color.text_main),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = { onSeeAllClick.invoke() }
                ),
                text = stringResource(com.kirchhoff.movies.screen.tvshow.R.string.tv_show_see_all),
                color = colorResource(R.color.link_color),
                fontSize = 16.sp
            )
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(
                count = tvShows.size,
                itemContent = {
                    TvShowDetailsSimilarItemUI(
                        tvShow = tvShows[it],
                        onItemClick = onItemClick
                    )
                }
            )
        }
    }
}
