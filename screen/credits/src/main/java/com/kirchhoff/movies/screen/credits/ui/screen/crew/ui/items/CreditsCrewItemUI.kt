package com.kirchhoff.movies.screen.credits.ui.screen.crew.ui.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewListItem
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewListPersonItem
import com.kirchhoff.movies.screen.credits.ui.screen.crew.ui.items.job.CreditsCrewJobItemUI
import com.kirchhoff.movies.screen.credits.ui.screen.crew.ui.items.persons.CreditsCrewPersonsItemUI

@Composable
internal fun CreditsCrewItemUI(
    item: CreditsCrewListItem,
    onItemClick: (String) -> Unit,
    onPersonCreditsItemClick: (CreditsCrewListPersonItem) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        CreditsCrewJobItemUI(
            jobText = item.job,
            isExpanded = item.isExpanded,
            onItemClick = onItemClick
        )
        CreditsCrewPersonsItemUI(
            persons = item.persons,
            isExpanded = item.isExpanded,
            onPersonCreditsItemClick = onPersonCreditsItemClick
        )
    }
}

@Preview
@Composable
private fun CreditsCrewItemUIPreview() {
    CreditsCrewItemUI(
        item = CreditsCrewListItem.Default,
        onItemClick = {},
        onPersonCreditsItemClick = {}
    )
}
