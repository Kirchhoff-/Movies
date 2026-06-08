package com.kirchhoff.movies.screen.movie.ui.screen.details

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.screen.movie.repository.MovieDetailsRepository
import com.kirchhoff.movies.screen.movie.ui.screen.details.usecase.MovieDetailsUseCase
import com.kirchhoff.movies.screen.movie.ui.screen.details.viewmodel.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val movieDetailsModule = module {

    single<MovieDetailsRepository> {
        MovieDetailsRepository(
            movieService = get(),
            movieStorage = get()
        )
    }

    single<MovieDetailsUseCase> {
        MovieDetailsUseCase(
            movieDetailsRepository = get(),
            movieDetailsMapper = get(),
            movieListMapper = get()
        )
    }

    viewModel { (movieId: MovieId) ->
        MovieDetailsViewModel(
            movieId = movieId,
            movieDetailsUseCase = get(),
            movieUseCase = get()
        )
    }
}
