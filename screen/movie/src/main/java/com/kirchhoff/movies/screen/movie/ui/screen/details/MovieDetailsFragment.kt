package com.kirchhoff.movies.screen.movie.ui.screen.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.extensions.addTitleWithCollapsingListener
import com.kirchhoff.movies.core.extensions.downloadPoster
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.extensions.setTextOrGone
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.decorations.EdgesMarginItemDecoration
import com.kirchhoff.movies.core.ui.utils.viewBinding
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.data.UIMovieDetails
import com.kirchhoff.movies.screen.movie.data.UITrailer
import com.kirchhoff.movies.screen.movie.databinding.FragmentMovieDetailsBinding
import com.kirchhoff.movies.screen.movie.ui.screen.details.adapter.MovieTrailerListAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

@SuppressWarnings("TooManyFunctions")
class MovieDetailsFragment : BaseFragment(R.layout.fragment_movie_details),
    BaseRecyclerViewAdapter.OnItemClickListener<UITrailer> {

    private val movie: UIMovie by lazy { requireArguments().getParcelableExtra(MOVIE_ARG)!! }

    private val vm by viewModel<MovieDetailsVM>()
    private val viewBinding by viewBinding(FragmentMovieDetailsBinding::bind)

    override fun onAttach(context: Context) {
        loadKoinModules(movieDetailsModule)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.loadMovieDetails(movie.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            ivBackdrop.downloadPoster(movie.backdropPath)
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
            appbar.addTitleWithCollapsingListener(toolbar, movie.title.orEmpty())
        }

        with(viewBinding.content.rvTrailers) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(
                EdgesMarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.trailer_item_margin)
                )
            )
        }

        with(viewBinding.content) {
            tvMovieTitle.text = movie.title
            ivMoviePoster.downloadPoster(movie.posterPath)
            bRetry.setOnClickListener { vm.loadMovieDetails(movie.id) }
            tvReviews.setOnClickListener { openReviewsListScreen(movie) }
            tvSimilarMovies.setOnClickListener { openSimilarMoviesScreen(movie) }
            vCredits.itemClickListener { openPersonDetailsScreen(it) }
            tvCountry.setOnClickListener {
                val countryId =
                    it.tag as? String ?: error("Should set countryId as tag for this TextView")
                openMoviesByCountryScreen(countryId, tvCountry.text.toString())
            }
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

    override fun onDestroyView() {
        unloadKoinModules(movieDetailsModule)
        super.onDestroyView()
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
            tvRuntime.isVisible = movieDetails.runtime != null
            tvRuntime.text = getString(
                R.string.movie_runtime_format,
                formatMovieRuntime(movieDetails.runtime),
                movieDetails.runtime
            )

            tvCountry.isVisible = movieDetails.productionCountries.isNotEmpty()
            if (movieDetails.productionCountries.isNotEmpty()) {
                val country = movieDetails.productionCountries.first()
                tvCountry.text = country.name
                tvCountry.tag = country.id
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
                rvTrailers.adapter = MovieTrailerListAdapter(trailersList, this@MovieDetailsFragment)
            }
        }
    }

    private fun handleMovieCredits(movieCredits: UIEntertainmentCredits) {
        with(viewBinding.content.vCredits) {
            isVisible = true
            display(movieCredits)
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

    private fun openReviewsListScreen(movie: UIMovie) {
        router.openReviewsListScreen(movie)
    }

    private fun openSimilarMoviesScreen(movie: UIMovie) {
        router.openSimilarMoviesScreen(movie)
    }

    private fun openPersonDetailsScreen(id: Int) {
        val person: UIEntertainmentPerson =
            vm.movieCredits.value?.findPerson(id) ?: error("Can't find person with id = $id")
        router.openPersonDetailsScreen(UIPerson(person))
    }

    private fun openMoviesByCountryScreen(countryId: String, countryName: String) {
        router.openMoviesByCountryScreen(countryId, countryName)
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
