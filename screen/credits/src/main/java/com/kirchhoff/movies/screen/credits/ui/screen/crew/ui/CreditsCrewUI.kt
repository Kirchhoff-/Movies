package com.kirchhoff.movies.screen.credits.ui.screen.crew.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewScreenState
import com.kirchhoff.movies.screen.credits.ui.screen.crew.ui.items.CreditsCrewItemUI

@Composable
internal fun CreditsCrewUI(
    screenState: CreditsCrewScreenState,
    onItemClick: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        MoviesToolbar(title = screenState.title.asString(context)) {
            onBackPressed.invoke()
        }
        LazyColumn {
            items(
                count = screenState.creators.size,
                itemContent = {
                    CreditsCrewItemUI(
                        item = screenState.creators[it],
                        onItemClick = onItemClick
                    )
                    Divider(color = Colors.Black)
                }
            )
        }
    }
}

@Preview
@Composable
private fun CreditsCrewUIPreview() {
    CreditsCrewUI(
        screenState = CreditsCrewScreenState.Default,
        onItemClick = {},
        onBackPressed = {}
    )
}
