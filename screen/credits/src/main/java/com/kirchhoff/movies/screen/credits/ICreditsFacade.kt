package com.kirchhoff.movies.screen.credits

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.UIEntertainmentPerson

interface ICreditsFacade {
    fun castCredits(actors: List<UIEntertainmentPerson.Actor>): Fragment
    fun crewCredits(): Fragment
}
