package com.kirchhoff.movies.screen.credits.ui.screen.crew

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.extensions.getParcelableArrayListExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.credits.ui.screen.crew.ui.CreditsCrewUI
import com.kirchhoff.movies.screen.credits.ui.screen.crew.viewmodel.CreditsCrewViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal class CreditsCrewFragment : BaseFragment() {

    private val creators: List<UIEntertainmentPerson.Creator> by lazy {
        requireArguments().getParcelableArrayListExtra(CREATORS_ARG)
            ?: error("Should provide creators list in arguments")
    }

    private val viewModel: CreditsCrewViewModel by viewModel { parametersOf(creators) }

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
            val screenState by viewModel.screenState.observeAsState()

            CreditsCrewUI(
                screenState = screenState ?: error("Can't build UI without state"),
                onItemClick = { viewModel.onItemClicked(it) },
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(creditsCrewModule)
        super.onDestroy()
    }

    companion object {
        fun newInstance(creators: List<UIEntertainmentPerson.Creator>): CreditsCrewFragment = CreditsCrewFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(CREATORS_ARG, ArrayList(creators))
            }
        }

        private const val CREATORS_ARG = "CREATORS_ARG"
    }
}
