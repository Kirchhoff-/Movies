package com.kirchhoff.movies.screen.credits.ui.screen.cast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.kirchhoff.movies.screen.credits.ui.screen.cast.ui.CreditsCastUI
import com.kirchhoff.movies.screen.credits.ui.screen.cast.viewmodel.CreditsCastViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal class CreditsCastFragment : BaseFragment() {

    private val actors: List<UIEntertainmentPerson.Actor> by lazy {
        requireArguments().getParcelableExtra(ACTORS_ARG)
            ?: error("Should provide actors list in arguments")
    }

    private val viewModel: CreditsCastViewModel by viewModel { parametersOf(actors) }

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
            CreditsCastUI()
        }
    }

    override fun onDestroy() {
        unloadKoinModules(creditsCastModule)
        super.onDestroy()
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
