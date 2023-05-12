package com.kirchhoff.movies.screen.movie.ui.screen.list

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenFragment
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.ui.screen.list.adapter.MovieListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListFragment : PaginatedScreenFragment<UIMovie, UIPaginated<UIMovie>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UIMovie> {

    override val vm by viewModel<MovieListVM>()

    override val listAdapter = MovieListAdapter(this)

    override val configuration: Configuration = Configuration(
        threshold = THRESHOLD,
        spanCount = SPAN_COUNT,
        emptyResultText = R.string.movie_empty_movies,
        isToolbarVisible = false,
        toolbarTitle = ""
    )

    companion object {
        private const val SPAN_COUNT = 2
        private const val THRESHOLD = SPAN_COUNT * 3
    }

    override fun onItemClick(item: UIMovie) {
        router.openMovieDetailsScreen(item)
    }
}
