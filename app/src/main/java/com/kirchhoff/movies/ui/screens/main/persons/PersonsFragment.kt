package com.kirchhoff.movies.ui.screens.main.persons

import com.kirchhoff.movies.data.Person
import com.kirchhoff.movies.data.responses.PersonsResponse
import com.kirchhoff.movies.ui.screens.main.MainScreenFragment
import com.kirchhoff.movies.ui.screens.main.persons.adapter.PersonsListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class PersonsFragment : MainScreenFragment<Person, PersonsResponse>() {

    override val vm by viewModel<PersonsVM>()

    override val listAdapter = PersonsListAdapter()

    override val threshold = PERSONS_THRESHOLD

    override val spanCount = SPAN_COUNT

    companion object {
        private const val SPAN_COUNT = 3
        private const val PERSONS_THRESHOLD = SPAN_COUNT * 2
    }
}
