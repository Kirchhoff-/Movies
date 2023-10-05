package com.kirchhoff.movies.screen.movie.ui.screen.list

import com.kirchhoff.movies.screen.movie.ui.screen.list.viewmodel.MovieListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieListModule = module {
    viewModel { (type: MovieListType) ->
        MovieListViewModel(
            type = type,
            movieRepository = get()
        )
    }
}
