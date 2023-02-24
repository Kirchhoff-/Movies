package com.kirchhoff.movies.ui.screens.main.persons

import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenFragment
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.data.ui.main.UIPerson
import com.kirchhoff.movies.ui.screens.details.DetailsActivity
import com.kirchhoff.movies.ui.screens.main.persons.adapter.PersonsListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class PersonsFragment : PaginatedScreenFragment<UIPerson, UIPaginated<UIPerson>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UIPerson> {

    override val vm by viewModel<PersonsVM>()

    override val listAdapter = PersonsListAdapter(this)

    override val configuration: Configuration = Configuration(
        threshold = THRESHOLD,
        spanCount = SPAN_COUNT,
        emptyResultText = R.string.empty_persons,
        isToolbarVisible = false,
        toolbarTitle = ""
    )

    companion object {
        private const val SPAN_COUNT = 3
        private const val THRESHOLD = SPAN_COUNT * 2
    }

    override fun onItemClick(item: UIPerson) {
        startActivity(DetailsActivity.createPersonDetailsIntent(requireContext(), item))
    }
}
