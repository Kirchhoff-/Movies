package com.kirchhoff.movies.screen.credits.ui.screen.cast.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.screen.credits.ui.screen.cast.model.CreditsCastScreenState

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
    }
}
