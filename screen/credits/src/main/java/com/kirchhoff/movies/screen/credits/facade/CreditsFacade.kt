package com.kirchhoff.movies.screen.credits.facade

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.screen.credits.ICreditsFacade
import com.kirchhoff.movies.screen.credits.ui.screen.cast.CreditsCastFragment
import com.kirchhoff.movies.screen.credits.ui.screen.crew.CreditsCrewFragment

class CreditsFacade : ICreditsFacade {

    override fun castCredits(actors: List<UIEntertainmentPerson.Actor>): Fragment =
        CreditsCastFragment.newInstance(actors)

    override fun crewCredits(): Fragment = CreditsCrewFragment()
}
