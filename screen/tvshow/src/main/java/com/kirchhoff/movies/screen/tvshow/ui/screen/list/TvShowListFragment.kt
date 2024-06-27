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
import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.ui.TvShowListUI
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.viewmodel.TvShowListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

internal class TvShowListFragment : BaseFragment() {

    private val type: TvShowListType by lazy {
        requireArguments().getParcelableExtra(TYPE_ARG)
            ?: error("Should provide type argument for fragment")
    }

    private val tvShowListModule: Module by lazy { tvShowListModule(type) }

    private val viewModel: TvShowListViewModel by viewModel()

    override fun onAttach(context: Context) {
        loadKoinModules(tvShowListModule)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.updateTitle()
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
                onTvShowClick = { router.openTvDetailsScreen(it) },
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(tvShowListModule)
        super.onDestroy()
    }

    companion object {

        fun similar(tvId: TvId): TvShowListFragment = createFragment(TvShowListType.Similar(tvId))

        fun airingToday(): TvShowListFragment = createFragment(TvShowListType.AiringToday)

        fun onTheAir(): TvShowListFragment = createFragment(TvShowListType.OnTheAir)

        fun popular(): TvShowListFragment = createFragment(TvShowListType.Popular)

        fun topRated(): TvShowListFragment = createFragment(TvShowListType.TopRated)

        private fun createFragment(type: TvShowListType): TvShowListFragment = TvShowListFragment().apply {
            arguments = Bundle().apply {
                putParcelable(TYPE_ARG, type)
            }
        }

        private const val TYPE_ARG = "TYPE_ARG"
    }
}
