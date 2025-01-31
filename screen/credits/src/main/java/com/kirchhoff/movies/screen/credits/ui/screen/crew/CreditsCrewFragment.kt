package com.kirchhoff.movies.screen.credits.ui.screen.crew

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIPerson
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.credits.ui.screen.crew.ui.CreditsCrewUI
import com.kirchhoff.movies.screen.credits.ui.screen.crew.viewmodel.CreditsCrewViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal class CreditsCrewFragment : BaseFragment() {

    private val viewModel: CreditsCrewViewModel by viewModel {
        parametersOf(requireArguments().getParcelableExtra(MOVIE_ID_ARG) ?: error("Should provide movieId in arguments"))
    }

    override fun onAttach(context: Context) {
        loadKoinModules(creditsCrewModule)
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
            val screenState by viewModel.screenState.collectAsStateWithLifecycle()

            CreditsCrewUI(
                screenState = screenState,
                onItemClick = { viewModel.onItemClicked(it) },
                onPersonCreditsItemClick = { crewCredits ->
                    router.openPersonDetailsScreen(
                        UIPerson(
                            id = crewCredits.id,
                            name = crewCredits.name,
                            profilePath = crewCredits.profilePath
                        )
                    )
                },
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(creditsCrewModule)
        super.onDestroy()
    }

    companion object {
        fun newInstance(movieId: MovieId): CreditsCrewFragment = CreditsCrewFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MOVIE_ID_ARG, movieId)
            }
        }

        private const val MOVIE_ID_ARG = "MOVIE_ID_ARG"
    }
}
