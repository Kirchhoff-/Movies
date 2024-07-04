@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.tvshow.ui.view.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.keywordsview.RatingText

@Composable
internal fun TvShowSectionItemUI(
    tvShow: UITv,
    onItemClick: (UITv) -> Unit
) {
    Card(
        modifier = Modifier
            .height(250.dp)
            .width(150.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = { onItemClick.invoke(tvShow) }
            ),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Box {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp),
                    model = BASE_POSTER_PATH + tvShow.posterPath,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.ic_empty_movie),
                    error = painterResource(R.drawable.ic_empty_movie),
                    contentDescription = ""
                )
                RatingText(
                    modifier = Modifier.align(Alignment.TopEnd),
                    voteAverage = tvShow.voteAverage
                )
            }
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = tvShow.name,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Colors.TextMain,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
private fun TvShowDetailsSimilarItemUIPreview() {
    TvShowSectionItemUI(
        tvShow = UITv(
            id = TvId(0),
            name = "Name",
            posterPath = null,
            backdropPath = null,
            voteAverage = 10.0f
        ),
        onItemClick = {}
    )
}
