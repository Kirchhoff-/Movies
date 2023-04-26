package com.kirchhoff.movies.ui.screens.similar.movie

import android.os.Bundle
import android.view.View
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenFragment
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.ui.screens.core.movies.adapter.MoviesListAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SimilarMoviesFragment : PaginatedScreenFragment<UIMovie, UIPaginated<UIMovie>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UIMovie> {

    override val vm: SimilarMoviesVM by viewModel { parametersOf(requireArguments().getInt(MOVIE_ID_ARG)) }

    override val listAdapter = MoviesListAdapter(this)

    override val configuration: Configuration = Configuration(
        threshold = THRESHOLD,
        spanCount = SPAN_COUNT,
        emptyResultText = R.string.empty_similar_movies,
        isToolbarVisible = true,
        toolbarTitle = ""
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayTitle(getString(R.string.similar_to_format, requireArguments().getString(MOVIE_TITLE_ARG)))
    }

    override fun onItemClick(item: UIMovie) {
        router.openMovieDetailsScreen(item)
    }

    companion object {
        fun newInstance(movieId: Int, movieTitle: String?): SimilarMoviesFragment =
            SimilarMoviesFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_ID_ARG, movieId)
                    putString(MOVIE_TITLE_ARG, movieTitle)
                }
            }

        private const val MOVIE_ID_ARG = "MOVIE_ID_ARG"
        private const val MOVIE_TITLE_ARG = "MOVIE_TITLE_ARG"
        private const val SPAN_COUNT = 2
        private const val THRESHOLD = SPAN_COUNT * 3
    }
}
