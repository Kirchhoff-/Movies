package com.kirchhoff.movies.di

import com.kirchhoff.movies.screen.movie.ui.screen.details.MovieDetailsVM
import com.kirchhoff.movies.screen.movie.ui.screen.list.MovieListVM
import com.kirchhoff.movies.screen.person.ui.screen.details.PersonDetailsVM
import com.kirchhoff.movies.screen.person.ui.screen.list.PersonsVM
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.TvShowDetailsVM
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.TvShowListVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieListVM(movieRepository = get()) }
    viewModel { TvShowListVM(discoverRepository = get()) }
    viewModel { PersonsVM(personRepository = get()) }
    viewModel { MovieDetailsVM(movieRepository = get()) }
    viewModel { TvShowDetailsVM(tvRepository = get()) }
    viewModel { PersonDetailsVM(personRepository = get()) }
}
