package com.kirchhoff.movies.screen.movie.ui.screen.list

import com.kirchhoff.movies.screen.movie.ui.screen.list.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val movieListModule = module {
    viewModel { (type: MovieListType) ->
        MovieListViewModel(
            type = type,
            movieRepository = get()
        )
    }
}
