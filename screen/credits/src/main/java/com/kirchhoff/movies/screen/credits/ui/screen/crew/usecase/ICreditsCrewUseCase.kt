package com.kirchhoff.movies.screen.credits.ui.screen.crew.usecase

import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewListItem

internal interface ICreditsCrewUseCase {
    fun createCrewList(
        creators: List<UIEntertainmentPerson.Creator>,
        expandedItems: Set<String>
    ): List<CreditsCrewListItem>
}
