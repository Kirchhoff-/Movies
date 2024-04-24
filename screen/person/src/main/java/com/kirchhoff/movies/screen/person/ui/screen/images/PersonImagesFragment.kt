package com.kirchhoff.movies.screen.person.ui.screen.images

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.person.ui.screen.images.ui.PersonImagesUI
import com.kirchhoff.movies.screen.person.ui.screen.images.viewmodel.PersonImagesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal class PersonImagesFragment : BaseFragment() {

    private val viewModel: PersonImagesViewModel by viewModel {
        parametersOf(
            requireArguments().getInt(PERSON_ID_ARG),
            requireArguments().getInt(START_POSITION_ARG)
        )
    }

    override fun onAttach(context: Context) {
        loadKoinModules(personImagesModule)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val screenState by viewModel.screenState.observeAsState()

            PersonImagesUI(
                screenState = screenState ?: error("Can't build UI without state"),
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(personImagesModule)
        super.onDestroy()
    }

    companion object {
        fun newInstance(personId: Int, startPosition: Int): PersonImagesFragment = PersonImagesFragment().apply {
            arguments = Bundle().apply {
                putInt(PERSON_ID_ARG, personId)
                putInt(START_POSITION_ARG, startPosition)
            }
        }

        private const val PERSON_ID_ARG = "PERSON_ID_ARG"
        private const val START_POSITION_ARG = "START_POSITION_ARG"
    }
}
