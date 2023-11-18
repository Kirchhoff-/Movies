package com.kirchhoff.movies.screen.credits.ui.screen.crew.usecase

import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewListItem

internal class CreditsCrewUseCase(
    private val creditsCrewUseCase: ICreditsCrewUseCase
) : ICreditsCrewUseCase {
    override fun createCrewList(creators: List<UIEntertainmentPerson.Creator>): List<CreditsCrewListItem> =
        creditsCrewUseCase.createCrewList(creators)
}
