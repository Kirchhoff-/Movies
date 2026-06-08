package com.kirchhoff.movies.screen.person

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.screen.person.ui.screen.details.PersonDetailsFragment
import com.kirchhoff.movies.screen.person.ui.screen.list.PersonListFragment

class PersonFacade {
    fun personList(): Fragment = PersonListFragment()

    fun personDetails(personId: Int): Fragment = PersonDetailsFragment.newInstance(personId)
}
