package com.kirchhoff.movies.di

import com.kirchhoff.movies.ui.screens.main.movies.MoviesVM
import com.kirchhoff.movies.ui.screens.main.persons.PersonsVM
import com.kirchhoff.movies.ui.screens.main.tvs.TvsVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MoviesVM(discoverRepository = get()) }
    viewModel { TvsVM(discoverRepository = get()) }
    viewModel { PersonsVM(personRepository = get()) }
}
