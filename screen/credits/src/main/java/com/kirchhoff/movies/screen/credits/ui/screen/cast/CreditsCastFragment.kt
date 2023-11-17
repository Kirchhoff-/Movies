package com.kirchhoff.movies.screen.credits.ui.screen.cast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.credits.ui.screen.cast.ui.CreditsCastUI

internal class CreditsCastFragment : BaseFragment() {

    private val actors: List<UIEntertainmentPerson.Actor> by lazy {
        requireArguments().getParcelableExtra(ACTORS_ARG)
            ?: error("Should provide actors list in arguments")
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
            CreditsCastUI()
        }
    }

    companion object {
        fun newInstance(actors: List<UIEntertainmentPerson.Actor>): CreditsCastFragment = CreditsCastFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ACTORS_ARG, ArrayList(actors))
                }
        }

        private const val ACTORS_ARG = "ACTORS_ARG"
    }
}
