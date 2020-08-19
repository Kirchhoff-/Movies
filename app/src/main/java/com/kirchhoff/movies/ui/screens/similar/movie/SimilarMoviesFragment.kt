package com.kirchhoff.movies.ui.screens.similar.movie

import android.os.Bundle
import com.kirchhoff.movies.data.ui.core.PaginatedData
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenFragment
import com.kirchhoff.movies.ui.screens.core.movies.adapter.MoviesListAdapter
import com.kirchhoff.movies.ui.screens.details.DetailsActivity
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SimilarMoviesFragment : PaginatedScreenFragment<UIMovie, PaginatedData<UIMovie>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UIMovie> {

    override val vm: SimilarMoviesVM by viewModel { parametersOf(arguments!!.getInt(MOVIE_ID_ARG)) }

    override val listAdapter = MoviesListAdapter(this)

    override val threshold = MOVIES_THRESHOLD

    override val spanCount = SPAN_COUNT

    override fun onItemClick(item: UIMovie) {
        startActivity(DetailsActivity.createMovieDetailsIntent(requireContext(), item))
    }

    companion object {
        fun newInstance(movieId: Int): SimilarMoviesFragment {
            val fragment = SimilarMoviesFragment()
            val arg = Bundle()
            arg.putInt(MOVIE_ID_ARG, movieId)
            fragment.arguments = arg
            return fragment
        }

        private const val MOVIE_ID_ARG = "MOVIE_ID_ARG"
        private const val SPAN_COUNT = 2
        private const val MOVIES_THRESHOLD = SPAN_COUNT * 3
    }
}
