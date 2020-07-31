package com.kirchhoff.movies.ui.screens.details.tv

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Tv
import com.kirchhoff.movies.data.responses.TvCredits
import com.kirchhoff.movies.data.responses.TvDetails
import com.kirchhoff.movies.databinding.FragmentTvDetailsBinding
import com.kirchhoff.movies.extensions.downloadPoster
import com.kirchhoff.movies.ui.screens.BaseFragment
import com.kirchhoff.movies.ui.screens.reviews.ReviewsActivity
import com.kirchhoff.movies.ui.screens.similar.SimilarActivity
import com.kirchhoff.movies.utils.binding.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TvDetailsFragment : BaseFragment(R.layout.fragment_tv_details) {

    private val tv: Tv by lazy { arguments!!.getParcelable<Tv>(TV_ARG)!! }

    private val vm by viewModel<TvDetailsVM>()
    private val viewBinding: FragmentTvDetailsBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.loadTvDetails(tv.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            ivBackdrop.downloadPoster(tv.backdrop_path)
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        }

        with(viewBinding.content) {
            tvTvTitle.text = tv.name
            ivTvPoster.downloadPoster(tv.poster_path)
            bRetry.setOnClickListener { vm.loadTvDetails(tv.id) }
            tvSimilarTv.setOnClickListener { startActivity(SimilarActivity.createSimilarTvIntent(requireContext(), tv)) }
            tvReviews.setOnClickListener { startActivity(ReviewsActivity.createTvIntent(requireContext(), tv)) }
        }

        with(vm) {
            tvDetails.subscribe(::handleTvDetailsData)
            tvCredits.subscribe(::handleTvCredits)
            loading.subscribe(::handleLoading)
            error.subscribe(::handleError)
            exception.subscribe(::handleException)
        }
    }

    private fun handleTvDetailsData(tvDetails: TvDetails) {
        with(viewBinding.content) {
            groupData.isVisible = true

            tvSeasons.text = resources.getString(R.string.tv_seasons_format, tvDetails.number_of_seasons)
            tvEpisodes.text = resources.getString(R.string.tv_episodes_format, tvDetails.number_of_episodes)
            tvFirstAirDate.text = resources.getString(R.string.tv_first_air_date_format, tvDetails.first_air_date)
            tvStatus.text = resources.getString(R.string.tv_status_format, tvDetails.status)
            tvOverview.text = tvDetails.overview
            voteView.displayRatingAndVoteCount(tvDetails.vote_average, tvDetails.vote_count)
            vKeywords.displayItems(tvDetails.genres.map { it.name })
        }
    }

    private fun handleTvCredits(tvCredits: TvCredits) {
        with(viewBinding.content) {
            if (!tvCredits.cast.isNullOrEmpty()) {
                castCreditsGroup.isVisible = true
                vCastCredits.displayItems(tvCredits.cast)
            }

            if (!tvCredits.crew.isNullOrEmpty()) {
                crewCreditsGroup.isVisible = true
                vCrewCredits.displayItems(tvCredits.crew)
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

    companion object {
        fun newInstance(tv: Tv): TvDetailsFragment {
            val fragment = TvDetailsFragment()
            val arg = Bundle()
            arg.putParcelable(TV_ARG, tv)
            fragment.arguments = arg
            return fragment
        }

        private const val TV_ARG = "TV_ARG"
    }
}
