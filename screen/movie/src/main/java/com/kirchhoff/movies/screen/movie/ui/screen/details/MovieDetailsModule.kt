package com.kirchhoff.movies.screen.movie.ui.screen.details

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.screen.movie.ui.screen.details.viewmodel.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val movieDetailsModule = module {
    viewModel { (movie: UIMovie) ->
        MovieDetailsViewModel(
            movie = movie,
            movieRepository = get()
        )
    }
}
