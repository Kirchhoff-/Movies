package com.kirchhoff.movies.screen.movie.ui.screen.details

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailsModule = module {
    viewModel { MovieDetailsViewModel(movieRepository = get()) }
}
