@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.tvshow.ui.screen.list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.data.ui.UITv
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.keywordsview.RatingText

@Composable
internal fun TvShowListItemUI(
    tvShow: UITv,
    onTvShowClick: (UITv) -> Unit
) {
    val cornerRadius = 8.dp

    Column(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = { onTvShowClick.invoke(tvShow) }
            )
    ) {
        Box {
            AsyncImage(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .shadow(10.dp)
                    .clip(shape = RoundedCornerShape(cornerRadius)),
                model = BASE_POSTER_PATH + tvShow.posterPath,
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
            RatingText(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                voteAverage = tvShow.voteAverage,
                fontSize = 20.sp
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomStart)
                    .background(
                        color = Colors.GrayTextBackground,
                        shape = RoundedCornerShape(
                            bottomStart = cornerRadius,
                            bottomEnd = cornerRadius
                        )
                    )
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center,
                text = tvShow.name,
                color = Colors.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun TvShowListItemUIPreview() {
    TvShowListItemUI(
        tvShow = UITv.Default,
        onTvShowClick = {}
    )
}
