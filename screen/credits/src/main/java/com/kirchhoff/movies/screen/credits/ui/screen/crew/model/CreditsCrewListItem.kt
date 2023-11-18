package com.kirchhoff.movies.screen.credits.ui.screen.crew.model

internal data class CreditsCrewListItem(
    val job: String,
    val persons: List<CreditsCrewListCreatorItem>
)
