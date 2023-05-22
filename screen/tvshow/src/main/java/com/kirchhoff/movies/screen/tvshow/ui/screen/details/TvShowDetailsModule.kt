package com.kirchhoff.movies.screen.tvshow.ui.screen.details

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tvShowDetailsModule = module {
    viewModel { TvShowDetailsVM(tvRepository = get()) }
}
