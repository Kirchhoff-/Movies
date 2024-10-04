package com.kirchhoff.movies.screen.movie.ui.screen.discover

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
import com.kirchhoff.movies.screen.movie.movieModule
import com.kirchhoff.movies.screen.movie.router.IMovieRouter
import com.kirchhoff.movies.screen.movie.ui.screen.discover.ui.MovieDiscoverUI
import com.kirchhoff.movies.screen.movie.ui.screen.discover.viewmodel.MovieDiscoverViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal class MovieDiscoverFragment : BaseFragment() {

    private val movieRouter: IMovieRouter by inject { parametersOf(requireActivity()) }

    private val viewModel: MovieDiscoverViewModel by viewModel()

    override fun onAttach(context: Context) {
        loadKoinModules(movieModule)
        loadKoinModules(movieDiscoverModule)
        super.onAttach(context)
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

            MovieDiscoverUI(
                screenState = screenState ?: error("Can't build UI without state"),
                onMovieClick = { router.openMovieDetailsScreen(it.id) },
                onNowPlayingClick = { movieRouter.openNowPlayingScreen() },
                onPopularClick = { movieRouter.openPopularScreen() },
                onTopRatedClick = { movieRouter.openTopRatedScreen() },
                onUpcomingClick = { movieRouter.openUpcomingScreen() }
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(movieModule)
        unloadKoinModules(movieDiscoverModule)
        super.onDestroy()
    }

    companion object {
        fun newInstance(): MovieDiscoverFragment = MovieDiscoverFragment()
    }
}
