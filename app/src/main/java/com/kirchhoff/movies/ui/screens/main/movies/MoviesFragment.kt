package com.kirchhoff.movies.ui.screens.main.movies

import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.data.responses.DiscoverMoviesResponse
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenFragment
import com.kirchhoff.movies.ui.screens.core.movies.adapter.MoviesListAdapter
import com.kirchhoff.movies.ui.screens.details.DetailsActivity
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : PaginatedScreenFragment<Movie, DiscoverMoviesResponse>(),
    BaseRecyclerViewAdapter.OnItemClickListener<Movie> {

    override val vm by viewModel<MoviesVM>()

    override val listAdapter = MoviesListAdapter(this)

    override val threshold = MOVIES_THRESHOLD

    override val spanCount = SPAN_COUNT

    companion object {
        private const val SPAN_COUNT = 2
        private const val MOVIES_THRESHOLD = SPAN_COUNT * 3
    }

    override fun onItemClick(item: Movie) {
        startActivity(DetailsActivity.createMovieDetailsIntent(requireContext(), item))
    }
}
