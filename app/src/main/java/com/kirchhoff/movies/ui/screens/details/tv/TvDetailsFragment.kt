package com.kirchhoff.movies.ui.screens.details.tv

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.extensions.addTitleWithCollapsingListener
import com.kirchhoff.movies.core.extensions.downloadPoster
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.core.ui.utils.viewBinding
import com.kirchhoff.movies.creditsview.CreditsView
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.core.UIEntertainmentPerson
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails
import com.kirchhoff.movies.data.ui.main.UIPerson
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.databinding.FragmentTvDetailsBinding
import com.kirchhoff.movies.screen.review.ui.screen.list.ReviewsListFragment
import com.kirchhoff.movies.ui.screens.details.person.PersonDetailsFragment
import com.kirchhoff.movies.ui.screens.similar.tv.SimilarTvsFragment
import org.koin.android.viewmodel.ext.android.viewModel

class TvDetailsFragment : BaseFragment(R.layout.fragment_tv_details) {

    private val tv: UITv by lazy { requireArguments().getParcelable(TV_ARG)!! }

    private val vm by viewModel<TvDetailsVM>()
    private val viewBinding by viewBinding(FragmentTvDetailsBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.loadTvDetails(tv.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            ivBackdrop.downloadPoster(tv.backdropPath)
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
            appbar.addTitleWithCollapsingListener(toolbar, tv.name.orEmpty())
        }

        with(viewBinding.content) {
            tvTvTitle.text = tv.name
            ivTvPoster.downloadPoster(tv.posterPath)
            bRetry.setOnClickListener { vm.loadTvDetails(tv.id) }
            tvSimilarTv.setOnClickListener { openSimilarTvShowsScreen(tv) }
            tvReviews.setOnClickListener { openReviewsListScreen(tv) }
            vCredits.setCastClickListener { openPersonDetailsScreen(it) }
            vCredits.setCrewClickListener { openPersonDetailsScreen(it) }
        }

        with(vm) {
            tvDetails.subscribe(::handleTvDetailsData)
            tvCredits.subscribe(::handleTvCredits)
            loading.subscribe(::handleLoading)
            error.subscribe(::handleError)
            exception.subscribe(::handleException)
        }
    }

    private fun handleTvDetailsData(tvDetails: UITvDetails) {
        with(viewBinding.content) {
            groupData.isVisible = true

            tvSeasons.text = resources.getString(R.string.tv_seasons_format, tvDetails.numberOfSeasons)
            tvEpisodes.text = resources.getString(R.string.tv_episodes_format, tvDetails.numberOfEpisodes)
            tvFirstAirDate.text = resources.getString(R.string.tv_first_air_date_format, tvDetails.firstAirDate)
            tvStatus.text = resources.getString(R.string.tv_status_format, tvDetails.status)
            tvOverview.text = tvDetails.overview
            voteView.displayRatingAndVoteCount(tvDetails.voteAverage, tvDetails.voteCount)
            vKeywords.displayItems(tvDetails.genres.map { it.name })
        }
    }

    private fun handleTvCredits(tvCredits: UIEntertainmentCredits) {
        with(viewBinding.content.vCredits) {
            isVisible = true
            displayItems(tvCredits.cast, tvCredits.crew)
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

    private fun openReviewsListScreen(tv: UITv) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, ReviewsListFragment.newInstanceForTvShow(tv.id, tv.name))
            .addToBackStack(null)
            .commit()
    }

    private fun openSimilarTvShowsScreen(tv: UITv) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, SimilarTvsFragment.newInstance(tv.id, tv.name))
            .addToBackStack(null)
            .commit()
    }

    private fun openPersonDetailsScreen(creditsInfo: CreditsView.CreditsInfo) {
        val person: UIPerson = when (creditsInfo) {
            is UIEntertainmentPerson -> UIPerson(creditsInfo.id, creditsInfo.name, creditsInfo.profilePath)
            else -> error("Can't create UIPerson from creditsInfo = $creditsInfo")
        }

        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, PersonDetailsFragment.newInstance(person))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance(tv: UITv): TvDetailsFragment {
            val fragment = TvDetailsFragment()
            val arg = Bundle()
            arg.putParcelable(TV_ARG, tv)
            fragment.arguments = arg
            return fragment
        }

        private const val TV_ARG = "TV_ARG"
    }
}
