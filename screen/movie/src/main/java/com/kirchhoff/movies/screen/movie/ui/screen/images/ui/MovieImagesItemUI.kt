package com.kirchhoff.movies.screen.movie.ui.screen.images.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.extensions.pxToDp

@SuppressWarnings("MagicNumber")
@Composable
internal fun MovieImagesItemUI(
    image: UIImage,
    onImageClick: (String) -> Unit
) {
    AsyncImage(
        modifier = Modifier
            .height(image.height.pxToDp())
            .fillMaxWidth()
            .shadow(10.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = { onImageClick.invoke(image.path) }
            ),
        model = BASE_POSTER_PATH + image.path,
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.ic_account_circle),
        error = painterResource(R.drawable.ic_account_circle),
        contentDescription = ""
    )
}

@Preview
@Composable
internal fun MovieImagesItemUIPreview() {
    MovieImagesItemUI(
        UIImage(
            path = "",
            height = 96,
            width = 96
        ),
        onImageClick = {}
    )
}
