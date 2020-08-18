package com.kirchhoff.movies.ui.screens.main.persons

import com.kirchhoff.movies.data.ui.core.PaginatedData
import com.kirchhoff.movies.data.ui.main.UIPerson
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenFragment
import com.kirchhoff.movies.ui.screens.details.DetailsActivity
import com.kirchhoff.movies.ui.screens.main.persons.adapter.PersonsListAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class PersonsFragment : PaginatedScreenFragment<UIPerson, PaginatedData<UIPerson>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UIPerson> {

    override val vm by viewModel<PersonsVM>()

    override val listAdapter = PersonsListAdapter(this)

    override val threshold = PERSONS_THRESHOLD

    override val spanCount = SPAN_COUNT

    companion object {
        private const val SPAN_COUNT = 3
        private const val PERSONS_THRESHOLD = SPAN_COUNT * 2
    }

    override fun onItemClick(item: UIPerson) {
        startActivity(DetailsActivity.createPersonDetailsIntent(requireContext(), item))
    }
}
