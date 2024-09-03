package com.kirchhoff.movies.screen.credits.ui.screen.crew.model

import com.kirchhoff.movies.core.utils.StringValue

internal data class CreditsCrewScreenState(
    val creators: List<CreditsCrewListItem>,
    val title: StringValue
) {
    companion object {
        val Default = CreditsCrewScreenState(
            creators = emptyList(),
            title = StringValue.Empty
        )
    }
}
