package com.kirchhoff.movies.screen.credits.facade

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.screen.credits.ICreditsFacade
import com.kirchhoff.movies.screen.credits.ui.screen.cast.CreditsCastFragment
import com.kirchhoff.movies.screen.credits.ui.screen.crew.CreditsCrewFragment

class CreditsFacade : ICreditsFacade {

    override fun castCredits(): Fragment = CreditsCastFragment()

    override fun crewCredits(): Fragment = CreditsCrewFragment()
}
