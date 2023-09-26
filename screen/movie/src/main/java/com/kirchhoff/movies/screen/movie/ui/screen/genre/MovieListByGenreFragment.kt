package com.kirchhoff.movies.screen.movie.ui.screen.genre

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
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.movie.ui.screen.genre.ui.MovieListByGenreUI
import com.kirchhoff.movies.screen.movie.ui.screen.genre.viewmodel.MovieListByGenreViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class MovieListByGenreFragment : BaseFragment() {

    private val genre: UIGenre by lazy {
        requireArguments().getParcelableExtra(GENRE_ARG)
            ?: error("Should provide genre argument for fragment")
    }

    private val viewModel: MovieListByGenreViewModel by viewModel {
        parametersOf(genre)
    }

    override fun onAttach(context: Context) {
        loadKoinModules(movieListByGenreModule)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

            MovieListByGenreUI(
                screenState = screenState ?: error("Can't build UI without state"),
                onLoadMore = { viewModel.loadMovieList() },
                onMovieClick = { router.openMovieDetailsScreen(it) },
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    override fun onDestroyView() {
        unloadKoinModules(movieListByGenreModule)
        super.onDestroyView()
    }

    companion object {
        fun newInstance(genre: UIGenre): MovieListByGenreFragment =
            MovieListByGenreFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GENRE_ARG, genre)
                }
            }

        private const val GENRE_ARG = "GENRE_ARG"
    }
}
