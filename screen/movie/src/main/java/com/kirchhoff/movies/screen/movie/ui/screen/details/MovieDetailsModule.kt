package com.kirchhoff.movies.screen.movie.ui.screen.details

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailsModule = module {
    viewModel { MovieDetailsVM(movieRepository = get()) }
}
