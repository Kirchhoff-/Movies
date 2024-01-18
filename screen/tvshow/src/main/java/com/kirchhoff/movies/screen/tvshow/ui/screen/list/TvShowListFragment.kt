package com.kirchhoff.movies.screen.tvshow.ui.screen.list

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
import com.kirchhoff.movies.screen.tvshow.tvShowModule
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.ui.TvShowListUI
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.viewmodel.TvShowListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

internal class TvShowListFragment : BaseFragment() {

    private val viewModel: TvShowListViewModel by viewModel()

    override fun onAttach(context: Context) {
        loadKoinModules(tvShowModule)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadTvShows()
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

            TvShowListUI(
                screenState = screenState ?: error("Can't build UI without state"),
                onLoadMore = { viewModel.loadTvShows() },
                onTvShowClick = { router.openTvDetailsScreen(it) }
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(tvShowModule)
        super.onDestroy()
    }
}
