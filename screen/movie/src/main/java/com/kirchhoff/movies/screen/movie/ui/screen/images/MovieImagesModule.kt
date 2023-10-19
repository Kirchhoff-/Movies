package com.kirchhoff.movies.screen.movie.ui.screen.images

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.screen.movie.ui.screen.images.usecase.IMovieImagesUseCase
import com.kirchhoff.movies.screen.movie.ui.screen.images.usecase.MovieImagesUseCase
import com.kirchhoff.movies.screen.movie.ui.screen.images.viewmodel.MovieImagesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val movieImagesModule = module {
    single<IMovieImagesUseCase> { MovieImagesUseCase(movieRepository = get()) }

    viewModel { (movie: UIMovie) ->
        MovieImagesViewModel(
            movie = movie,
            movieImagesUseCase = get()
        )
    }
}
