package com.kirchhoff.movies.screen.credits.ui.screen.crew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.extensions.getParcelableArrayListExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.credits.ui.screen.crew.ui.CreditsCrewUI

internal class CreditsCrewFragment : BaseFragment() {

    private val creators: List<UIEntertainmentPerson.Creator> by lazy {
        requireArguments().getParcelableArrayListExtra(CREATORS_ARG)
            ?: error("Should provide creators list in arguments")
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
            CreditsCrewUI()
        }
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
