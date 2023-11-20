package com.kirchhoff.movies.screen.credits.ui.screen.crew.usecase

import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.screen.credits.ui.screen.crew.factory.ICreditsCrewListFactory
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewListItem

internal class CreditsCrewUseCase(
    private val creditsCrewListFactory: ICreditsCrewListFactory
) : ICreditsCrewUseCase {
    override fun createCrewList(
        creators: List<UIEntertainmentPerson.Creator>,
        expandedItems: Set<String>
    ): List<CreditsCrewListItem> = creditsCrewListFactory.createCrewList(creators, expandedItems)
}
