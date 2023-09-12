package com.kirchhoff.movies.screen.person.ui.screen.list.ui

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.ui.compose.LazyStaggeredGridScrollHandler
import com.kirchhoff.movies.screen.person.ui.screen.list.model.PersonListScreenState

@OptIn(ExperimentalFoundationApi::class)
@SuppressWarnings("MagicNumber")
@Composable
internal fun PersonListUI(
    screenState: PersonListScreenState,
    onLoadMore: () -> Unit,
    onPersonClick: (UIPerson) -> Unit
) {
    val context = LocalContext.current
    val gridState = rememberLazyStaggeredGridState()
    var errorMessage by rememberSaveable { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            state = gridState
        ) {
            items(
                count = screenState.personList.size,
                itemContent = { PersonListItemUI(screenState.personList[it], onPersonClick) }
            )
        }
        LazyStaggeredGridScrollHandler(gridState = gridState) {
            onLoadMore()
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = screenState.loadingVisible
        ) {
            CircularProgressIndicator()
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.BottomStart),
            visible = screenState.paginationVisible
        ) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }

    if (screenState.errorMessage.isNotEmpty() && errorMessage.isEmpty()) {
        errorMessage = screenState.errorMessage
        SideEffect {
            Toast.makeText(context, screenState.errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}

@SuppressWarnings("MagicNumber")
@Preview
@Composable
internal fun PersonListUIPreview() {
    PersonListUI(
        screenState = PersonListScreenState(
            personList = listOf(
                UIPerson(
                    id = 0,
                    name = "John Doe",
                    profilePath = null
                ),
                UIPerson(
                    id = 0,
                    name = "John Doe",
                    profilePath = null
                )
            ),
            errorMessage = "Some error message",
            loadingVisible = true,
            paginationVisible = false
        ),
        onLoadMore = {},
        onPersonClick = { UIPerson(3, "Random name", null) }
    )
}
