package com.kirchhoff.movies.ui.screens.details.movie

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.data.responses.MovieDetails
import com.kirchhoff.movies.databinding.FragmentMovieDetailsBinding
import com.kirchhoff.movies.extensions.downloadPoster
import com.kirchhoff.movies.extensions.setTextOrGone
import com.kirchhoff.movies.ui.screens.BaseFragment
import com.kirchhoff.movies.ui.screens.reviews.ReviewsActivity
import com.kirchhoff.movies.utils.binding.viewBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
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
            content.bRetry.setOnClickListener { vm.loadMovieDetails(movie.id) }
            ivBackdrop.downloadPoster(movie.backdrop_path)
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
            content.tvReviews.setOnClickListener { startActivity(ReviewsActivity.createReviewsIntent(requireContext(), movie)) }
        }

        with(vm) {
            movieDetails.subscribe(::handleMovieDetailsData)
            loading.subscribe(::handleLoading)
            error.subscribe(::handleError)
            exception.subscribe(::handleException)
        }
    }

    private fun handleMovieDetailsData(movieDetails: MovieDetails) {
        with(viewBinding.content) {
            groupData.isVisible = true

            tvOverview.text = movieDetails.overview
            tvReleaseDate.setTextOrGone(movieDetails.release_date)
            tvTagline.setTextOrGone(movieDetails.tagline)
            tvRuntime.setTextOrGone(
                movieDetails.runtime, getString(
                    R.string.movie_runtime_format,
                    formatMovieRuntime(movieDetails.runtime),
                    movieDetails.runtime
                )
            )

            tvCountry.isVisible = movieDetails.production_countries.isNotEmpty()
            if (movieDetails.production_countries.isNotEmpty()) {
                tvCountry.text = movieDetails.production_countries.first().name
            }
        }
    }

    private fun handleLoading(visible: Boolean) {
        with(viewBinding) {
            content.groupLoading.isVisible = visible

            if (visible) {
                content.groupException.isVisible = false
                content.groupError.isVisible = false
                content.groupData.isVisible = false
            }
        }
    }

    private fun handleError(error: String) {
        with(viewBinding) {
            content.groupError.isVisible = true
            content.tvError.text = error
        }
    }

    private fun handleException(exception: String) {
        with(viewBinding) {
            content.groupException.isVisible = true
            content.tvException.text = exception
        }
    }

    private fun formatMovieRuntime(runtime: Int?) =
        if (runtime == null) {
            ""
        } else {
            val minuteFormat = SimpleDateFormat("mm", Locale.ENGLISH)
            val hourFormat = SimpleDateFormat("H:mm", Locale.ENGLISH)

            hourFormat.format(minuteFormat.parse(runtime.toString()) as Date)
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
