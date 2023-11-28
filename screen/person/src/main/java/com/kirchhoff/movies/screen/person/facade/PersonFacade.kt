package com.kirchhoff.movies.screen.person.facade

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.screen.person.IPersonFacade
import com.kirchhoff.movies.screen.person.ui.screen.details.OldPersonDetailsFragment
import com.kirchhoff.movies.screen.person.ui.screen.list.PersonListFragment

class PersonFacade : IPersonFacade {

    override fun personList(): Fragment = PersonListFragment()

    override fun personDetails(person: UIPerson): Fragment = OldPersonDetailsFragment.newInstance(person)
}
