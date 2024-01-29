package com.kirchhoff.movies.screen.tvshow.ui.screen.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.ui.TvShowDetailsUI
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal class NewTvShowDetailsFragment : BaseFragment() {

    private val tvShow: UITv by lazy {
        requireArguments().getParcelableExtra(TV_ARG)
            ?: error("Should provide tv show info in arguments")
    }

    private val viewModel: NewTvShowDetailsViewModel by viewModel { parametersOf(tvShow) }

    override fun onAttach(context: Context) {
        loadKoinModules(newTvShowDetailsModule)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDetails()
    }

    @ExperimentalLayoutApi
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
                onCreditItemClick = { onCreditItemClick(it) },
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(newTvShowDetailsModule)
        super.onDestroy()
    }

    private fun onCreditItemClick(credit: UIEntertainmentPerson) {
        router.openPersonDetailsScreen(UIPerson(credit))
    }

    companion object {
        fun newInstance(tv: UITv): NewTvShowDetailsFragment = NewTvShowDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(TV_ARG, tv)
            }
        }

        private const val TV_ARG = "TV_ARG"
    }
}
