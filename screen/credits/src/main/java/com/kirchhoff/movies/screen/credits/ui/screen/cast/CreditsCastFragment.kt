package com.kirchhoff.movies.screen.credits.ui.screen.cast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.credits.ui.screen.cast.ui.CreditsCastUI
import com.kirchhoff.movies.screen.credits.ui.screen.cast.viewmodel.CreditsCastViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal class CreditsCastFragment : BaseFragment() {

    private val viewModel: CreditsCastViewModel by viewModel {
        parametersOf(requireArguments().getParcelableExtra(MOVIE_ID_ARG) ?: error("Should provide movieId in arguments"))
    }

    override fun onAttach(context: Context) {
        loadKoinModules(creditsCastModule)
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

            CreditsCastUI(
                screenState = screenState ?: error("Can't build UI without state"),
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(creditsCastModule)
        super.onDestroy()
    }

    companion object {
        fun newInstance(movieId: MovieId): CreditsCastFragment = CreditsCastFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MOVIE_ID_ARG, movieId)
            }
        }

        private const val MOVIE_ID_ARG = "MOVIE_ID_ARG"
    }
}
