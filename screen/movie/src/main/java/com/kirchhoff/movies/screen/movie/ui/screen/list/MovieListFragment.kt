package com.kirchhoff.movies.screen.movie.ui.screen.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.kirchhoff.movies.core.data.UIGenre
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.movie.data.UIProductionCompany
import com.kirchhoff.movies.screen.movie.movieModule
import com.kirchhoff.movies.screen.movie.ui.screen.list.ui.MovieListUI
import com.kirchhoff.movies.screen.movie.ui.screen.list.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal class MovieListFragment : BaseFragment() {

    private val type: MovieListType by lazy {
        requireArguments().getParcelableExtra(TYPE_ARG)
            ?: error("Should provide type argument for fragment")
    }

    private val viewModel: MovieListViewModel by viewModel {
        parametersOf(type)
    }

    override fun onAttach(context: Context) {
        if (type == MovieListType.Discover) {
            loadKoinModules(movieModule)
        }
        loadKoinModules(movieListModule)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.updateTitle()
        viewModel.loadMovieList()
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

            MovieListUI(
                screenState = screenState ?: error("Can't build UI without state"),
                onLoadMore = { viewModel.loadMovieList() },
                onMovieClick = { router.openMovieDetailsScreen(it) },
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    override fun onDestroyView() {
        if (type != MovieListType.Discover) unloadKoinModules(movieListModule)
        super.onDestroyView()
    }

    override fun onDestroy() {
        if (type == MovieListType.Discover) unloadKoinModules(movieModule)
        super.onDestroy()
    }

    companion object {

        fun discover(): MovieListFragment = createFragment(MovieListType.Discover)

        fun byGenre(genre: UIGenre): MovieListFragment = createFragment(MovieListType.Genre(genre))

        fun byCountry(countryId: String, countryName: String): MovieListFragment = createFragment(
            MovieListType.Country(countryId, countryName)
        )

        fun byCompany(company: UIProductionCompany): MovieListFragment = createFragment(
            MovieListType.Company(company)
        )

        fun similarWith(movie: UIMovie): MovieListFragment = createFragment(MovieListType.Similar(movie))

        private fun createFragment(type: MovieListType): MovieListFragment = MovieListFragment().apply {
            arguments = Bundle().apply {
                putParcelable(TYPE_ARG, type)
            }
        }

        private const val TYPE_ARG = "TYPE_ARG"
    }
}
