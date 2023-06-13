package com.kirchhoff.movies.screen.movie.ui.screen.similar

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieSimilarModule = module {
    viewModel { (movieId: Int) ->
        MovieSimilarViewModel(
            movieId = movieId,
            movieRepository = get()
        )
    }
}
