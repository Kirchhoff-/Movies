package com.kirchhoff.movies.screen.tvshow.ui.screen.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.kirchhoff.movies.core.data.ui.UIPerson
import com.kirchhoff.movies.core.data.ui.UITv
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.tvshow.router.ITvShowRouter
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.ui.TvShowDetailsUI
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.viewmodel.TvShowDetailsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal class TvShowDetailsFragment : BaseFragment() {

    private val tvShow: UITv by lazy {
        requireArguments().getParcelableExtra(TV_ARG)
            ?: error("Should provide tv show info in arguments")
    }

    private val tvShowRouter: ITvShowRouter by inject { parametersOf(requireActivity()) }

    private val viewModel: TvShowDetailsViewModel by viewModel { parametersOf(tvShow) }

    override fun onAttach(context: Context) {
        loadKoinModules(tvShowDetailsModule)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDetails()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
        )

        setContent {
            val screenState by viewModel.screenState.observeAsState()

            TvShowDetailsUI(
                screenState = screenState ?: error("Can't build UI without state"),
                onCreditItemClick = { router.openPersonDetailsScreen(UIPerson(it)) },
                onReviewsClick = { router.openReviewsListScreen(tvShow) },
                onSimilarItemClick = { router.openTvDetailsScreen(it) },
                onSimilarSeeAllClick = { tvShowRouter.openSimilarTvShowScreen(tvShow.id) },
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(tvShowDetailsModule)
        super.onDestroy()
    }

    companion object {
        fun newInstance(tv: UITv): TvShowDetailsFragment = TvShowDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(TV_ARG, tv)
            }
        }

        private const val TV_ARG = "TV_ARG"
    }
}
