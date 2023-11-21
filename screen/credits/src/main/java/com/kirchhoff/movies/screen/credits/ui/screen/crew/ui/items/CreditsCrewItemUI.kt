package com.kirchhoff.movies.screen.credits.ui.screen.crew.ui.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewListItem
import com.kirchhoff.movies.screen.credits.ui.screen.crew.ui.items.persons.CreditsCrewPersonsItemUI
import com.kirchhoff.movies.screen.credits.ui.screen.crew.ui.items.job.CreditsCrewJobItemUI

@Composable
internal fun CreditsCrewItemUI(
    item: CreditsCrewListItem,
    onItemClick: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        CreditsCrewJobItemUI(
            jobText = item.job,
            onItemClick = onItemClick
        )
        CreditsCrewPersonsItemUI(
            persons = item.persons,
            isExpanded = item.isExpanded
        )
    }
}

@Preview
@Composable
internal fun CreditsCrewItemUIPreview() {
    CreditsCrewItemUI(
        CreditsCrewListItem(
            job = "Creator",
            persons = emptyList(),
            isExpanded = false
        ),
        onItemClick = {}
    )
}
