package com.kirchhoff.movies.ui.screens.details.movie

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.data.responses.MovieDetails
import com.kirchhoff.movies.databinding.FragmentMovieDetailsBinding
import com.kirchhoff.movies.extensions.downloadPoster
import com.kirchhoff.movies.ui.screens.BaseFragment
import com.kirchhoff.movies.utils.binding.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsFragment : BaseFragment(R.layout.fragment_movie_details) {

    private val movie: Movie by lazy { arguments!!.getParcelable<Movie>(MOVIE_ARG)!! }

    private val vm by viewModel<MovieDetailsVM>()
    private val viewBinding: FragmentMovieDetailsBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.loadMovieDetails(movie.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            content.tvMovieTitle.text = movie.title
            content.ivMoviePoster.downloadPoster(movie.poster_path)
            ivBackdrop.downloadPoster(movie.backdrop_path)
        }

        with(vm) {
            movieDetails.subscribe(::handleMovieDetailsData)
            loading.subscribe(::handleLoading)
            error.subscribe { handleError() }
            exception.subscribe { handleException() }
        }
    }

    private fun handleMovieDetailsData(movieDetails: MovieDetails) {
        with (viewBinding.content) {
            groupData.isVisible = true

            tvReleaseDate.isVisible = movieDetails.release_date != null
            tvReleaseDate.text = movieDetails.release_date

            if (movieDetails.runtime != null) {
                tvRuntime.isVisible = true
                tvRuntime.text = movieDetails.runtime.toString()
            } else {
                tvRuntime.isVisible = false
            }

            if (movieDetails.production_countries.isNotEmpty()) {
                tvCountry.isVisible = true
                tvCountry.text = movieDetails.production_countries.first().name
            } else {
                tvCountry.isVisible = false
            }

            tvTagline.isVisible = movieDetails.tagline != null
            tvTagline.text = movieDetails.tagline

            tvOverview.text = movieDetails.overview
        }
    }

    private fun handleLoading(visible: Boolean) {
        viewBinding.content.groupLoading.isVisible = visible
    }

    private fun handleError() {
        viewBinding.content.groupError.isVisible = true
    }

    private fun handleException() {
        viewBinding.content.groupException.isVisible = true
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