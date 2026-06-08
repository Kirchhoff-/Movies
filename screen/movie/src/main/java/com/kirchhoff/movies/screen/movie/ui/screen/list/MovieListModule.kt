package com.kirchhoff.movies.screen.movie.ui.screen.list

import com.kirchhoff.movies.screen.movie.ui.screen.list.repository.MovieListRepository
import com.kirchhoff.movies.screen.movie.ui.screen.list.usecase.MovieListTitleUseCase
import com.kirchhoff.movies.screen.movie.ui.screen.list.usecase.MovieListUseCase
import com.kirchhoff.movies.screen.movie.ui.screen.list.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val movieListModule = module {

    single<MovieListRepository> {
        MovieListRepository(
            movieService = get(),
            movieStorage = get()
        )
    }

    single<MovieListUseCase> {
        MovieListUseCase(
            movieListRepository = get(),
            movieListMapper = get()
        )
    }

    single<MovieListTitleUseCase> {
        MovieListTitleUseCase(movieRepository = get())
    }

    viewModel { (type: MovieListType) ->
        MovieListViewModel(
            type = type,
            movieListUseCase = get(),
            movieListTitleUseCase = get()
        )
    }
}
