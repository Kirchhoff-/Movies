package com.kirchhoff.movies.screen.credits.ui.screen.crew.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewListItem

@Composable
internal fun CreditsCrewItemUI(item: CreditsCrewListItem) {
    Box(
        modifier = Modifier.background(Color.DarkGray)
    ) {
        Column {
            CreditsCrewListJobUI(jobText = item.job)
        }
    }
}

@Preview
@Composable
internal fun CreditsCrewItemUIPreview() {
    CreditsCrewItemUI(
        CreditsCrewListItem(
            job = "Creator",
            persons = emptyList()
        )
    )
}
