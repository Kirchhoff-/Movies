package com.kirchhoff.movies.screen.credits.ui.screen.crew.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.core.utils.StringValue
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
                    Divider(color = Color.Black)
                }
            )
        }
    }
}

@Preview
@Composable
private fun CreditsCrewUIPreview() {
    CreditsCrewUI(
        screenState = CreditsCrewScreenState(
            creators = emptyList(),
            title = StringValue.Empty
        ),
        onItemClick = {},
        onBackPressed = {}
    )
}
