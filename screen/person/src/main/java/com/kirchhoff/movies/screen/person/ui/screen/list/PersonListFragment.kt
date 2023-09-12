package com.kirchhoff.movies.screen.person.ui.screen.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.person.personModule
import com.kirchhoff.movies.screen.person.ui.screen.list.ui.PersonListUI
import com.kirchhoff.movies.screen.person.ui.screen.list.viewmodel.PersonListViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class PersonListFragment : BaseFragment() {

    private val viewModel: PersonListViewModel by viewModel()

    override fun onAttach(context: Context) {
        loadKoinModules(personModule)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadPersonList()
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

            PersonListUI(
                screenState = screenState ?: error("Can't build UI without state"),
                onLoadMore = { viewModel.loadPersonList() },
                onPersonClick = { router.openPersonDetailsScreen(it) }
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(personModule)
        super.onDestroy()
    }
}
