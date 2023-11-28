package com.kirchhoff.movies.screen.person.ui.screen.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.person.ui.screen.details.ui.PersonDetailsUI
import com.kirchhoff.movies.screen.person.ui.screen.details.viewmodel.PersonDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal class PersonDetailsFragment : BaseFragment() {

    private val person: UIPerson by lazy {
        requireArguments().getParcelableExtra(PERSON_ARG)
            ?: error("Should provide person info in arguments")
    }

    private val viewModel: PersonDetailsViewModel by viewModel { parametersOf(person.id) }

    override fun onAttach(context: Context) {
        loadKoinModules(personDetailsModule)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDetails()
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

            PersonDetailsUI(screenState = screenState ?: error("Can't build UI without state"))
        }
    }


    override fun onDestroy() {
        unloadKoinModules(personDetailsModule)
        super.onDestroy()
    }

    companion object {
        fun newInstance(person: UIPerson): PersonDetailsFragment = PersonDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(PERSON_ARG, person)
            }
        }

        private const val PERSON_ARG = "PERSON_ARG"
    }
}
