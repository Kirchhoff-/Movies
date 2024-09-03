package com.kirchhoff.movies.screen.person.ui.screen.images.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.screen.person.R
import com.kirchhoff.movies.screen.person.ui.screen.images.model.PersonImagesScreenState

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun PersonImagesUI(
    screenState: PersonImagesScreenState,
    onBackPressed: () -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = screenState.startPosition,
        pageCount = { screenState.imagesUrls.size }
    )
    val currentTitle = stringResource(
        R.string.person_images_title_format,
        pagerState.currentPage + 1,
        screenState.imagesUrls.size
    )

    Column(modifier = Modifier.fillMaxSize()) {
        MoviesToolbar(title = currentTitle) { onBackPressed.invoke() }

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            beyondBoundsPageCount = 2,
            state = pagerState
        ) { page ->
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = BASE_POSTER_PATH + screenState.imagesUrls[page].url,
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
        }
    }
}

@Preview
@Composable
private fun PersonImagesUIPreview() {
    PersonImagesUI(
        screenState = PersonImagesScreenState.Default,
        onBackPressed = {}
    )
}
