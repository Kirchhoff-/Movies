package com.kirchhoff.movies.screen.tvshow.ui.screen.details

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.extensions.addTitleWithCollapsingListener
import com.kirchhoff.movies.core.extensions.downloadPoster
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.core.ui.utils.viewBinding
import com.kirchhoff.movies.screen.tvshow.R
import com.kirchhoff.movies.screen.tvshow.data.UITvShowDetails
import com.kirchhoff.movies.screen.tvshow.databinding.FragmentTvShowDetailsBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class TvShowDetailsFragment : BaseFragment(R.layout.fragment_tv_show_details) {

    private val tv: UITv by lazy { requireArguments().getParcelableExtra(TV_ARG)!! }

    private val vm by viewModel<TvShowDetailsVM>()
    private val viewBinding by viewBinding(FragmentTvShowDetailsBinding::bind)

    override fun onAttach(context: Context) {
        loadKoinModules(tvShowDetailsModule)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.loadTvDetails(tv.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            ivBackdrop.downloadPoster(tv.backdropPath)
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
            appbar.addTitleWithCollapsingListener(toolbar, tv.name.orEmpty())
        }

        with(viewBinding.content) {
            tvTvTitle.text = tv.name
            ivTvPoster.downloadPoster(tv.posterPath)
            bRetry.setOnClickListener { vm.loadTvDetails(tv.id) }
            tvSimilarTv.setOnClickListener { openSimilarTvShowsScreen(tv) }
            tvReviews.setOnClickListener { openReviewsListScreen(tv) }
            vCredits.itemClickListener { openPersonDetailsScreen(it) }
        }

        with(vm) {
            tvDetails.subscribe(::handleTvDetailsData)
            tvCredits.subscribe(::handleTvCredits)
            loading.subscribe(::handleLoading)
            error.subscribe(::handleError)
            exception.subscribe(::handleException)
        }
    }

    override fun onDestroy() {
        unloadKoinModules(tvShowDetailsModule)
        super.onDestroy()
    }

    private fun handleTvDetailsData(tvDetails: UITvShowDetails) {
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
            display(tvCredits)
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
        router.openReviewsListScreen(tv)
    }

    private fun openSimilarTvShowsScreen(tv: UITv) {
        router.openSimilarTvShowsScreen(tv)
    }

    private fun openPersonDetailsScreen(id: Int) {
        val person: UIEntertainmentPerson = vm.tvCredits.value?.findPerson(id) ?: error("Can't find person with id = $id")
        router.openPersonDetailsScreen(UIPerson(person))
    }

    companion object {
        fun newInstance(tv: UITv): TvShowDetailsFragment {
            val fragment = TvShowDetailsFragment()
            val arg = Bundle()
            arg.putParcelable(TV_ARG, tv)
            fragment.arguments = arg
            return fragment
        }

        private const val TV_ARG = "TV_ARG"
    }
}
