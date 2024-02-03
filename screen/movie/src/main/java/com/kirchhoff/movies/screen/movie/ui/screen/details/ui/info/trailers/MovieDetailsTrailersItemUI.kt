package com.kirchhoff.movies.screen.movie.ui.screen.details.ui.info.trailers

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.screen.movie.data.UITrailer

@Composable
internal fun MovieDetailsTrailersItemUI(
    trailer: UITrailer,
    onTrailerClick: (UITrailer) -> Unit
) {
    val resultImage = if (trailer.site.equals(YOUTUBE, true)) R.drawable.ic_youtube else R.drawable.ic_play_arrow

    Card(
        modifier = Modifier
            .height(150.dp)
            .width(200.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = { onTrailerClick.invoke(trailer) }
            ),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = String.format(YOUTUBE_POSTER_PATH, trailer.key),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
            Image(
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .align(Alignment.Center),
                painter = painterResource(resultImage),
                contentDescription = ""
            )
        }
    }
}

private const val YOUTUBE = "youtube"
private const val YOUTUBE_POSTER_PATH = "https://img.youtube.com/vi/%s/0.jpg"

@Preview
@Composable
private fun MovieDetailsTrailersItemUIPreview() {
    MovieDetailsTrailersItemUI(
        trailer = UITrailer(
            site = "",
            key = ""
        ),
        onTrailerClick = {}
    )
}
