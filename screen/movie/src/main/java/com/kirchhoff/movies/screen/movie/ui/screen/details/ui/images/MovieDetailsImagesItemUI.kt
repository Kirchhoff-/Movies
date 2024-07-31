@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.movie.ui.screen.details.ui.images

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH

@Composable
internal fun MovieDetailsImagesItemUI(
    image: UIImage,
    onItemClick: (UIImage) -> Unit
) {
    Card(
        modifier = Modifier
            .height(250.dp)
            .width(150.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = { onItemClick.invoke(image) }
            ),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = BASE_POSTER_PATH + image.path,
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
        }
    }
}

@Preview
@Composable
private fun MovieDetailsImagesItemUIPreview() {
    MovieDetailsImagesItemUI(
        image = UIImage.Default,
        onItemClick = {}
    )
}
