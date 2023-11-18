package com.kirchhoff.movies.screen.credits.ui.screen.crew.factory

import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewListItem

internal class CreditsCrewListFactory : ICreditsCrewListFactory {
    override fun createCrewList(creators: List<UIEntertainmentPerson.Creator>): List<CreditsCrewListItem> {
        return emptyList()
    }
}
