package com.kirchhoff.movies.screen.movie.ui.screen.list

import com.kirchhoff.movies.screen.movie.ui.screen.list.repository.IMovieListRepository
import com.kirchhoff.movies.screen.movie.ui.screen.list.repository.MovieListRepository
import com.kirchhoff.movies.screen.movie.ui.screen.list.usecase.IMovieListUseCase
import com.kirchhoff.movies.screen.movie.ui.screen.list.usecase.MovieListUseCase
import com.kirchhoff.movies.screen.movie.ui.screen.list.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val movieListModule = module {

    single<IMovieListRepository> {
        MovieListRepository(
            movieService = get(),
            movieStorage = get()
        )
    }

    single<IMovieListUseCase> {
        MovieListUseCase(
            movieListRepository = get(),
            movieListMapper = get()
        )
    }

    viewModel { (type: MovieListType) ->
        MovieListViewModel(
            type = type,
            movieListUseCase = get()
        )
    }
}
