package com.kirchhoff.movies.screen.movie.ui.screen.genre

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieListByGenreModule = module {
    viewModel { (genre: String) ->
        MovieListByGenreViewModel(
            genre = genre,
            movieRepository = get()
        )
    }
}
