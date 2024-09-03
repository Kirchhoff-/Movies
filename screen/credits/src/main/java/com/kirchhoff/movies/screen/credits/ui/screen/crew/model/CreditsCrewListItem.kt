package com.kirchhoff.movies.screen.credits.ui.screen.crew.model

internal data class CreditsCrewListItem(
    val job: String,
    val persons: List<CreditsCrewListPersonItem>,
    val isExpanded: Boolean
) {
    companion object {
        val Default = CreditsCrewListItem(
            job = "",
            persons = emptyList(),
            isExpanded = false
        )
    }
}
