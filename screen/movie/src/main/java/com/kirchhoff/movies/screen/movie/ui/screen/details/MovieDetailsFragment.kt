package com.kirchhoff.movies.screen.movie.ui.screen.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIPerson
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.movie.router.IMovieRouter
import com.kirchhoff.movies.screen.movie.ui.screen.details.ui.MovieDetailsUI
import com.kirchhoff.movies.screen.movie.ui.screen.details.viewmodel.MovieDetailsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal class MovieDetailsFragment : BaseFragment() {

    private val movieId: MovieId by lazy {
        val id = requireArguments().getInt(MOVIE_ID_ARG, MOVIE_ID_DEFAULT_VALUE)
        if (id == MOVIE_ID_DEFAULT_VALUE) error("Should provide movie id in arguments")
        MovieId(id)
    }

    private val movieRouter: IMovieRouter by inject { parametersOf(requireActivity()) }

    private val viewModel: MovieDetailsViewModel by viewModel { parametersOf(movieId) }

    override fun onAttach(context: Context) {
        loadKoinModules(movieDetailsModule)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDetails()
    }

    @ExperimentalLayoutApi
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

            MovieDetailsUI(
                screenState = screenState ?: error("Can't build UI without state"),
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() },
                onProductionCountryClick = { movieRouter.openMoviesByCountryScreen(it) },
                onGenreClick = { movieRouter.openMoviesByGenreScreen(it) },
                onTrailerClick = { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_VIDEO_URL + it.key))) },
                onCreditItemClick = { router.openPersonDetailsScreen(UIPerson(it)) },
                onCastSeeAllClick = { router.openCastCreditsScreen(movieId) },
                onCrewSeeAllClick = { router.openCrewCreditsScreen(movieId) },
                onSimilarMovieClick = { router.openMovieDetailsScreen(it.id) },
                onSimilarMovieSeeAllClick = { movieRouter.openSimilarMoviesScreen(movieId) },
                onImageItemClick = { movieRouter.openImage(it.path) },
                onImageSeeAllClick = { movieRouter.openImagesScreen(movieId) },
                onReviewsClick = { router.openReviewsListScreen(movieId) },
                onProductionCompanyClick = { movieRouter.openCompanyMoviesScreen(it) }
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(movieDetailsModule)
        super.onDestroy()
    }

    companion object {
        fun newInstance(movieId: MovieId): MovieDetailsFragment = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(MOVIE_ID_ARG, movieId.value)
            }
        }

        private const val MOVIE_ID_ARG = "MOVIE_ARG"
        private const val YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v="
        private const val MOVIE_ID_DEFAULT_VALUE = -1
    }
}
