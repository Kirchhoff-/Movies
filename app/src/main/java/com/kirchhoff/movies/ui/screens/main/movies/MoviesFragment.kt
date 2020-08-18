package com.kirchhoff.movies.ui.screens.main.movies

import com.kirchhoff.movies.data.ui.core.PaginatedData
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenFragment
import com.kirchhoff.movies.ui.screens.core.movies.adapter.MoviesListAdapter
import com.kirchhoff.movies.ui.screens.details.DetailsActivity
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : PaginatedScreenFragment<UIMovie, PaginatedData<UIMovie>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UIMovie> {

    override val vm by viewModel<MoviesVM>()

    override val listAdapter = MoviesListAdapter(this)

    override val threshold = MOVIES_THRESHOLD

    override val spanCount = SPAN_COUNT

    companion object {
        private const val SPAN_COUNT = 2
        private const val MOVIES_THRESHOLD = SPAN_COUNT * 3
    }

    override fun onItemClick(item: UIMovie) {
        startActivity(DetailsActivity.createMovieDetailsIntent(requireContext(), item))
    }
}
