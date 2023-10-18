package com.kirchhoff.movies.screen.movie.ui.screen.images

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.movie.router.IMovieRouter
import com.kirchhoff.movies.screen.movie.ui.screen.images.ui.MovieImagesUI
import com.kirchhoff.movies.screen.movie.ui.screen.images.viewmodel.MovieImagesViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal class MovieImagesFragment : BaseFragment() {

    private val movie: UIMovie by lazy {
        requireArguments().getParcelableExtra(MOVIE_ARG)
            ?: error("Should provide UIMovie argument for fragment")
    }

    private val movieRouter: IMovieRouter by inject { parametersOf(requireActivity()) }

    private val viewModel: MovieImagesViewModel by viewModel {
        parametersOf(movie)
    }

    override fun onAttach(context: Context) {
        loadKoinModules(movieImagesModule)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadImages()
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

            MovieImagesUI(
                screenState = screenState ?: error("Can't build UI without state"),
                onImageClick = { movieRouter.openImage(it) },
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(movieImagesModule)
        super.onDestroy()
    }

    companion object {
        fun newInstance(movie: UIMovie): MovieImagesFragment =
            MovieImagesFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MOVIE_ARG, movie)
                }
            }

        private const val MOVIE_ARG = "MOVIE_ARG"
    }
}
