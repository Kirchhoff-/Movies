package com.kirchhoff.movies.ui.screens.main.persons

import com.kirchhoff.movies.data.Person
import com.kirchhoff.movies.data.responses.PersonsResponse
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenFragment
import com.kirchhoff.movies.ui.screens.details.DetailsActivity
import com.kirchhoff.movies.ui.screens.main.persons.adapter.PersonsListAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class PersonsFragment : PaginatedScreenFragment<Person, PersonsResponse>(),
    BaseRecyclerViewAdapter.OnItemClickListener<Person> {

    override val vm by viewModel<PersonsVM>()

    override val listAdapter = PersonsListAdapter(this)

    override val threshold = PERSONS_THRESHOLD

    override val spanCount = SPAN_COUNT

    companion object {
        private const val SPAN_COUNT = 3
        private const val PERSONS_THRESHOLD = SPAN_COUNT * 2
    }

    override fun onItemClick(item: Person) {
        startActivity(DetailsActivity.createPersonDetailsIntent(requireContext(), item))
    }
}
