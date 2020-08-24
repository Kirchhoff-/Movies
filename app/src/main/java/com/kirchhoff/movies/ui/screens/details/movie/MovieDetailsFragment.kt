package com.kirchhoff.movies.ui.screens.details.movie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.ui.details.movie.UIMovieCastCredit
import com.kirchhoff.movies.data.ui.details.movie.UIMovieCredits
import com.kirchhoff.movies.data.ui.details.movie.UIMovieCrewCredit
import com.kirchhoff.movies.data.ui.details.movie.UIMovieDetails
import com.kirchhoff.movies.data.ui.details.movie.UITrailer
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.data.ui.main.UIPerson
import com.kirchhoff.movies.databinding.FragmentMovieDetailsBinding
import com.kirchhoff.movies.extensions.downloadPoster
import com.kirchhoff.movies.extensions.setTextOrGone
import com.kirchhoff.movies.ui.screens.BaseFragment
import com.kirchhoff.movies.ui.screens.core.credits.CreditsView
import com.kirchhoff.movies.ui.screens.details.DetailsActivity
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
    BaseRecyclerViewAdapter.OnItemClickListener<UITrailer> {

    private val movie: UIMovie by lazy { arguments!!.getParcelable<UIMovie>(MOVIE_ARG)!! }

    private val vm by viewModel<MovieDetailsVM>()
    private val viewBinding: FragmentMovieDetailsBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.loadMovieDetails(movie.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            ivBackdrop.downloadPoster(movie.backdropPath)
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
            ivMoviePoster.downloadPoster(movie.posterPath)
            bRetry.setOnClickListener { vm.loadMovieDetails(movie.id) }
            tvReviews.setOnClickListener { startActivity(ReviewsActivity.createReviewsIntent(requireContext(), movie)) }
            tvSimilarMovies.setOnClickListener { startActivity(SimilarActivity.createSimilarMoviesIntent(requireContext(), movie)) }
            vCredits.setCastClickListener { creditsInfo -> startPersonDetailsActivity(creditsInfo) }
            vCredits.setCrewClickListener { creditsInfo -> startPersonDetailsActivity(creditsInfo) }
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

    override fun onItemClick(item: UITrailer) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_VIDEO_URL + item.key))
        startActivity(intent)
    }

    private fun handleMovieDetailsData(movieDetails: UIMovieDetails) {
        with(viewBinding.content) {
            groupData.isVisible = true

            tvOverview.text = movieDetails.overview
            tvReleaseDate.setTextOrGone(movieDetails.releaseDate)
            tvTagline.setTextOrGone(movieDetails.tagLine)
            tvRuntime.setTextOrGone(
                movieDetails.runtime, getString(
                    R.string.movie_runtime_format,
                    formatMovieRuntime(movieDetails.runtime),
                    movieDetails.runtime
                )
            )

            tvCountry.isVisible = movieDetails.productionCountries.isNotEmpty()
            if (movieDetails.productionCountries.isNotEmpty()) {
                tvCountry.text = movieDetails.productionCountries.first().name
            }

            voteView.displayRatingAndVoteCount(movieDetails.voteAverage, movieDetails.voteCount)
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

    private fun handleTrailers(trailersList: List<UITrailer>) {
        if (trailersList.isNotEmpty()) {
            with(viewBinding.content) {
                groupTrailers.isVisible = true
                rvTrailers.adapter = TrailersListAdapter(trailersList, this@MovieDetailsFragment)
            }
        }
    }

    private fun handleMovieCredits(movieCredits: UIMovieCredits) {
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

    private fun startPersonDetailsActivity(creditsInfo: CreditsView.CreditsInfo) {
        val person: UIPerson = when (creditsInfo) {
            is UIMovieCastCredit -> UIPerson(creditsInfo.id, creditsInfo.name, creditsInfo.profilePath)
            is UIMovieCrewCredit -> UIPerson(creditsInfo.id, creditsInfo.name, creditsInfo.profilePath)
            else -> error("Can't create UIPerson from creditsInfo = $creditsInfo")
        }

        startActivity(DetailsActivity.createPersonDetailsIntent(requireContext(), person))
    }

    companion object {
        fun newInstance(movie: UIMovie): MovieDetailsFragment {
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
