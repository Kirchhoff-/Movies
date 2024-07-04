package com.kirchhoff.movies.screen.tvshow.ui.screen.discover

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.tvshow.router.ITvShowRouter
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.ui.TvShowDiscoverUI
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.viewmodel.TvShowDiscoverViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal class TvShowDiscoverFragment : BaseFragment() {

    private val tvShowRouter: ITvShowRouter by inject { parametersOf(requireActivity()) }

    private val viewModel: TvShowDiscoverViewModel by viewModel()

    override fun onAttach(context: Context) {
        loadKoinModules(tvShowDiscoverModule)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.discover()
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

            TvShowDiscoverUI(
                screenState = screenState ?: error("Can't build UI without state"),
                onTvShowClick = { router.openTvDetailsScreen(it) },
                onAiringTodayClick = { tvShowRouter.openAiringTodayScreen() },
                onTheAirClick = { tvShowRouter.openOnTheAirScreen() },
                onPopularClick = { tvShowRouter.openPopularScreen() },
                onTopRatedClick = { tvShowRouter.openTopRatedScreen() }
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(tvShowDiscoverModule)
        super.onDestroy()
    }

    companion object {
        fun newInstance(): TvShowDiscoverFragment = TvShowDiscoverFragment()
    }
}
