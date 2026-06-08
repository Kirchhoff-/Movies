package com.kirchhoff.movies.screen.credits

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.screen.credits.ui.screen.cast.CreditsCastFragment
import com.kirchhoff.movies.screen.credits.ui.screen.crew.CreditsCrewFragment

class CreditsFacade {
    fun castCredits(movieId: MovieId): Fragment = CreditsCastFragment.newInstance(movieId)

    fun crewCredits(movieId: MovieId): Fragment = CreditsCrewFragment.newInstance(movieId)
}
