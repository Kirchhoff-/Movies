package com.kirchhoff.movies.ui.screens.main.movies

import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.data.responses.DiscoverMoviesResponse
import com.kirchhoff.movies.ui.screens.main.MainScreenFragment
import com.kirchhoff.movies.ui.screens.main.movies.adapter.MoviesListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : MainScreenFragment<Movie, DiscoverMoviesResponse>() {

    override val vm by viewModel<MoviesVM>()

    override val listAdapter = MoviesListAdapter()

    override val threshold = MOVIES_THRESHOLD

    override val spanCount = SPAN_COUNT

    companion object {
        private const val SPAN_COUNT = 2
        private const val MOVIES_THRESHOLD = SPAN_COUNT * 3
    }
}
