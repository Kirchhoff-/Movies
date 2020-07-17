package com.kirchhoff.movies.ui.screens.details.tv

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Tv
import com.kirchhoff.movies.data.responses.TvDetails
import com.kirchhoff.movies.databinding.FragmentTvDetailsBinding
import com.kirchhoff.movies.extensions.downloadPoster
import com.kirchhoff.movies.ui.screens.BaseFragment
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
            content.tvTvTitle.text = tv.name
            content.ivTvPoster.downloadPoster(tv.poster_path)
            content.bRetry.setOnClickListener { vm.loadTvDetails(tv.id) }
            ivBackdrop.downloadPoster(tv.backdrop_path)
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        }

        with(vm) {
            tvDetails.subscribe(::handleTvDetailsData)
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
