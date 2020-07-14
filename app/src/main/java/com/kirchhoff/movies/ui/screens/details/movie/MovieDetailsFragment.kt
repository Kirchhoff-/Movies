package com.kirchhoff.movies.ui.screens.details.movie

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.ui.screens.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsFragment : BaseFragment(R.layout.fragment_movie_details) {

    private val movie: Movie by lazy { arguments!!.getParcelable<Movie>(MOVIE_ARG)!! }

    private val vm by viewModel<MovieDetailsVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.loadMovieDetails(movie.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)

        tvTitle.text = movie.title
    }

    companion object {
        fun newInstance(movie: Movie): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val arg = Bundle()
            arg.putParcelable(MOVIE_ARG, movie)
            fragment.arguments = arg
            return fragment
        }

        private const val MOVIE_ARG = "MOVIE_ARG"
    }
}