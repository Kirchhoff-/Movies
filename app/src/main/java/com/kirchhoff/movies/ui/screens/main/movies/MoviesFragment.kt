package com.kirchhoff.movies.ui.screens.main.movies

import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenFragment
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.ui.screens.core.movies.adapter.MoviesListAdapter
import com.kirchhoff.movies.ui.screens.details.movie.MovieDetailsFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : PaginatedScreenFragment<UIMovie, UIPaginated<UIMovie>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UIMovie> {

    override val vm by viewModel<MoviesVM>()

    override val listAdapter = MoviesListAdapter(this)

    override val configuration: Configuration = Configuration(
        threshold = THRESHOLD,
        spanCount = SPAN_COUNT,
        emptyResultText = R.string.empty_movies,
        isToolbarVisible = false,
        toolbarTitle = ""
    )

    companion object {
        private const val SPAN_COUNT = 2
        private const val THRESHOLD = SPAN_COUNT * 3
    }

    override fun onItemClick(item: UIMovie) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, MovieDetailsFragment.newInstance(item))
            .addToBackStack(null)
            .commit()
    }
}
