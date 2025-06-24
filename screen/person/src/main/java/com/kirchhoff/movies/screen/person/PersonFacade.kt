package com.kirchhoff.movies.screen.person

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.screen.person.ui.screen.details.PersonDetailsFragment
import com.kirchhoff.movies.screen.person.ui.screen.list.PersonListFragment

interface IPersonFacade {
    fun personList(): Fragment
    fun personDetails(personId: Int): Fragment
}

class PersonFacade : IPersonFacade {

    override fun personList(): Fragment = PersonListFragment()

    override fun personDetails(personId: Int): Fragment = PersonDetailsFragment.newInstance(personId)
}
