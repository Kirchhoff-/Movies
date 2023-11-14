package com.kirchhoff.movies.screen.credits

import androidx.fragment.app.Fragment

interface ICreditsFacade {
    fun castCredits(): Fragment
    fun crewCredits(): Fragment
}
