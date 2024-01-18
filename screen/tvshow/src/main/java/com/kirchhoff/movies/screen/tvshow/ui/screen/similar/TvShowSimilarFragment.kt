package com.kirchhoff.movies.screen.tvshow.ui.screen.similar

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.tvshow.ui.screen.similar.ui.TvShowSimilarUI
import com.kirchhoff.movies.screen.tvshow.ui.screen.similar.viewmodel.TvShowSimilarViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal class TvShowSimilarFragment : BaseFragment() {

    private val tvShow: UITv by lazy {
        requireArguments().getParcelableExtra(TV_SHOW_ARG)
            ?: error("Should provide UITv argument for fragment")
    }

    private val viewModel: TvShowSimilarViewModel by viewModel {
        parametersOf(tvShow)
    }

    override fun onAttach(context: Context) {
        loadKoinModules(tvShowSimilarModule)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadSimilarTvShow()
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

            TvShowSimilarUI(
                screenState = screenState ?: error("Can't build UI without state"),
                onLoadMore = { viewModel.loadSimilarTvShow() },
                onTvShowClick = { router.openTvDetailsScreen(it) },
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    override fun onDestroyView() {
        unloadKoinModules(tvShowSimilarModule)
        super.onDestroyView()
    }

    companion object {
        fun newInstance(tvShow: UITv): TvShowSimilarFragment =
            TvShowSimilarFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(TV_SHOW_ARG, tvShow)
                }
            }

        private const val TV_SHOW_ARG = "TV_SHOW_ARG"
    }
}
