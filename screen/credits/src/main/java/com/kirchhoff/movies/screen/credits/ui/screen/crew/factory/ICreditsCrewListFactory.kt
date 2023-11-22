package com.kirchhoff.movies.screen.credits.ui.screen.crew.factory

import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewListItem

internal interface ICreditsCrewListFactory {
    fun createCrewList(
        creators: List<UIEntertainmentPerson.Creator>,
        expandedItems: Set<String>
    ): List<CreditsCrewListItem>
}
