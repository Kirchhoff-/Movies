package com.kirchhoff.movies.screen.movie.ui.screen.images

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.screen.movie.ui.screen.images.viewmodel.MovieImagesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val movieImagesModule = module {
    viewModel { (movieId: MovieId) ->
        MovieImagesViewModel(
            movieId = movieId,
            movieUseCase = get()
        )
    }
}
