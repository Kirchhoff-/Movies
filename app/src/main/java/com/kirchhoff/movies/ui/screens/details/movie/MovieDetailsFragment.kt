package com.kirchhoff.movies.ui.screens.details.movie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.data.Trailer
import com.kirchhoff.movies.data.responses.MovieCredits
import com.kirchhoff.movies.data.responses.MovieDetails
import com.kirchhoff.movies.databinding.FragmentMovieDetailsBinding
import com.kirchhoff.movies.extensions.downloadPoster
import com.kirchhoff.movies.extensions.setTextOrGone
import com.kirchhoff.movies.ui.screens.BaseFragment
import com.kirchhoff.movies.ui.screens.details.movie.adapters.TrailersListAdapter
import com.kirchhoff.movies.ui.screens.reviews.ReviewsActivity
import com.kirchhoff.movies.ui.screens.similar.SimilarActivity
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.decorations.EdgesMarginItemDecoration
import com.kirchhoff.movies.utils.binding.viewBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsFragment : BaseFragment(R.layout.fragment_movie_details),
    BaseRecyclerViewAdapter.OnItemClickListener<Trailer> {

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
            ivBackdrop.downloadPoster(movie.backdrop_path)
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        }

        with(viewBinding.content.rvTrailers) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(EdgesMarginItemDecoration(
                resources.getDimensionPixelSize(R.dimen.trailer_item_margin)
            ))
        }

        with(viewBinding.content) {
            tvMovieTitle.text = movie.title
            ivMoviePoster.downloadPoster(movie.poster_path)
            bRetry.setOnClickListener { vm.loadMovieDetails(movie.id) }
            tvReviews.setOnClickListener { startActivity(ReviewsActivity.createReviewsIntent(requireContext(), movie)) }
            tvSimilarMovies.setOnClickListener { startActivity(SimilarActivity.createSimilarMoviesIntent(requireContext(), movie)) }
        }

        with(vm) {
            movieDetails.subscribe(::handleMovieDetailsData)
            movieCredits.subscribe(::handleMovieCredits)
            loading.subscribe(::handleLoading)
            error.subscribe(::handleError)
            exception.subscribe(::handleException)
            trailers.subscribe(::handleTrailers)
        }
    }

    override fun onItemClick(item: Trailer) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_VIDEO_URL + item.key))
        startActivity(intent)
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

            voteView.displayRatingAndVoteCount(movieDetails.vote_average, movieDetails.vote_count)
            vKeywords.displayItems(movieDetails.genres.map { it.name })
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

    private fun handleTrailers(trailersList: List<Trailer>) {
        if (trailersList.isNotEmpty()) {
            with(viewBinding.content) {
                groupTrailers.isVisible = true
                rvTrailers.adapter = TrailersListAdapter(trailersList, this@MovieDetailsFragment)
            }
        }
    }

    private fun handleMovieCredits(movieCredits: MovieCredits) {
        with(viewBinding.content.vCredits) {
            isVisible = true
            displayItems(movieCredits.cast, movieCredits.crew)
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
        const val YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v="
    }
}
