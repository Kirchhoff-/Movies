package com.kirchhoff.movies.screen.credits.ui.screen.cast.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.screen.credits.ui.screen.cast.model.CreditsCastScreenState

@SuppressWarnings("MagicNumber")
@Composable
internal fun CreditsCastUI(
    screenState: CreditsCastScreenState,
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        MoviesToolbar(title = screenState.title.asString(context)) {
            onBackPressed.invoke()
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(
                count = screenState.actors.size,
                itemContent = {
                    CreditsCastItemUI(screenState.actors[it])
                }
            )
        }
    }
}

@Preview
@Composable
private fun CreditsCastUIPreview() {
    CreditsCastUI(
        screenState = CreditsCastScreenState.Default,
        onBackPressed = {}
    )
}
