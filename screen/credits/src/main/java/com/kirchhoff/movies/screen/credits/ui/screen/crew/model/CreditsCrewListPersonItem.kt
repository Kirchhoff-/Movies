package com.kirchhoff.movies.screen.credits.ui.screen.crew.model

internal data class CreditsCrewListPersonItem(
    val id: Int,
    val name: String,
    val profilePath: String?
) {
    companion object {
        val Default = CreditsCrewListPersonItem(
            id = 0,
            name = "",
            profilePath = null
        )
    }
}
