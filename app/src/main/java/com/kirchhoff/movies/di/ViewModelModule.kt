package com.kirchhoff.movies.di

import com.kirchhoff.movies.ui.screens.main.movies.MoviesVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MoviesVM(discoverRepository = get()) }
}