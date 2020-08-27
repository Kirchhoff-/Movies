package com.kirchhoff.movies.ui.screens.details.tv

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.core.UIEntertainmentPerson
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails
import com.kirchhoff.movies.data.ui.main.UIPerson
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.databinding.FragmentTvDetailsBinding
import com.kirchhoff.movies.extensions.downloadPoster
import com.kirchhoff.movies.ui.screens.BaseFragment
import com.kirchhoff.movies.ui.screens.core.credits.CreditsView
import com.kirchhoff.movies.ui.screens.details.DetailsActivity
import com.kirchhoff.movies.ui.screens.reviews.ReviewsActivity
import com.kirchhoff.movies.ui.screens.similar.SimilarActivity
import com.kirchhoff.movies.utils.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TvDetailsFragment : BaseFragment(R.layout.fragment_tv_details) {

    private val tv: UITv by lazy { arguments!!.getParcelable<UITv>(TV_ARG)!! }

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
        }

        with(viewBinding.content) {
            tvTvTitle.text = tv.name
            ivTvPoster.downloadPoster(tv.posterPath)
            bRetry.setOnClickListener { vm.loadTvDetails(tv.id) }
            tvSimilarTv.setOnClickListener { startActivity(SimilarActivity.createSimilarTvIntent(requireContext(), tv)) }
            tvReviews.setOnClickListener { startActivity(ReviewsActivity.createTvIntent(requireContext(), tv)) }
            vCredits.setCastClickListener { creditsInfo -> startPersonDetailsActivity(creditsInfo) }
            vCredits.setCrewClickListener { creditsInfo -> startPersonDetailsActivity(creditsInfo) }
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

    private fun startPersonDetailsActivity(creditsInfo: CreditsView.CreditsInfo) {
        val person: UIPerson = when (creditsInfo) {
            is UIEntertainmentPerson -> UIPerson(creditsInfo.id, creditsInfo.name, creditsInfo.profilePath)
            else -> error("Can't create UIPerson from creditsInfo = $creditsInfo")
        }

        startActivity(DetailsActivity.createPersonDetailsIntent(requireContext(), person))
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
