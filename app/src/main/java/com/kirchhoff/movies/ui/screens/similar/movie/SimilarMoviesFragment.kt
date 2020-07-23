package com.kirchhoff.movies.ui.screens.similar.movie

import android.os.Bundle
import com.kirchhoff.movies.R
import com.kirchhoff.movies.ui.screens.BaseFragment

class SimilarMoviesFragment : BaseFragment(R.layout.fragment_similar_movies) {

    companion object {

        fun newInstance(movieId: Int): SimilarMoviesFragment {
            val fragment = SimilarMoviesFragment()
            val arg = Bundle()
            arg.putInt(MOVIE_ID_ARG, movieId)
            fragment.arguments = arg
            return fragment
        }

        private const val MOVIE_ID_ARG = "MOVIE_ID_ARG"
    }
}