package com.kirchhoff.movies.screen.tvshow.ui.screen.details

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tvShowDetailsModule = module {
    viewModel { TvShowDetailsViewModel(tvRepository = get()) }
}
