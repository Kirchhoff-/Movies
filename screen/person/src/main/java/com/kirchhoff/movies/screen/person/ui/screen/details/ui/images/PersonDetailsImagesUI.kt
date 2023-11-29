package com.kirchhoff.movies.screen.person.ui.screen.details.ui.images

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.screen.person.data.UIPersonImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun PersonDetailsImagesUI(images: List<UIPersonImage>) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { images.size }
    )

    HorizontalPager(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth(),
        beyondBoundsPageCount = 2,
        state = pagerState
    ) { page ->
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = BASE_POSTER_PATH + images[page].url,
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
    }
}

@Preview
@Composable
private fun PersonDetailsImagesUIPreview() {
    PersonDetailsImagesUI(images = emptyList())
}
