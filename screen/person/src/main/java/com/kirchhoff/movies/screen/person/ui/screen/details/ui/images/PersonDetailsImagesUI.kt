@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.person.ui.screen.details.ui.images

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.screen.person.data.UIPersonImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun PersonDetailsImagesUI(
    images: List<UIPersonImage>,
    onItemClick: (Int) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { images.size }
    )

    Box {
        HorizontalPager(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            beyondBoundsPageCount = 2,
            state = pagerState
        ) { page ->
            AsyncImage(
                modifier = Modifier.fillMaxSize()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true),
                        onClick = { onItemClick.invoke(page) }
                    ),
                model = BASE_POSTER_PATH + images[page].url,
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
        }

        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) {
                    Colors.Primary
                } else {
                    Colors.LightGray
                }

                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PersonDetailsImagesUIPreview() {
    PersonDetailsImagesUI(
        images = emptyList(),
        onItemClick = {}
    )
}
