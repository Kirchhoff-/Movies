package com.kirchhoff.movies.screen.person

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.UIPerson

interface IPersonFacade {
    fun personList(): Fragment
    fun personDetails(person: UIPerson): Fragment
}
