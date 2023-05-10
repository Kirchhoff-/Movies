package com.kirchhoff.movies.di

import com.kirchhoff.movies.screen.person.ui.screen.details.PersonDetailsVM
import com.kirchhoff.movies.screen.person.ui.screen.list.PersonsVM
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.TvShowDetailsVM
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.TvShowListVM
import com.kirchhoff.movies.ui.screens.details.movie.MovieDetailsVM
import com.kirchhoff.movies.ui.screens.main.movies.MoviesVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MoviesVM(discoverRepository = get()) }
    viewModel { TvShowListVM(discoverRepository = get()) }
    viewModel { PersonsVM(personRepository = get()) }
    viewModel { MovieDetailsVM(movieRepository = get()) }
    viewModel { TvShowDetailsVM(tvRepository = get()) }
    viewModel { PersonDetailsVM(personRepository = get()) }
}
