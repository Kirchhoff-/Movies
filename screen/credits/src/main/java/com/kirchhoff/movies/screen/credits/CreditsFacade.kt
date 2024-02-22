package com.kirchhoff.movies.screen.credits

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.screen.credits.ui.screen.cast.CreditsCastFragment
import com.kirchhoff.movies.screen.credits.ui.screen.crew.CreditsCrewFragment

interface ICreditsFacade {
    fun castCredits(actors: List<UIEntertainmentPerson.Actor>): Fragment
    fun crewCredits(creators: List<UIEntertainmentPerson.Creator>): Fragment
}

class CreditsFacade : ICreditsFacade {

    override fun castCredits(actors: List<UIEntertainmentPerson.Actor>): Fragment =
        CreditsCastFragment.newInstance(actors)

    override fun crewCredits(creators: List<UIEntertainmentPerson.Creator>): Fragment =
        CreditsCrewFragment.newInstance(creators)
}
