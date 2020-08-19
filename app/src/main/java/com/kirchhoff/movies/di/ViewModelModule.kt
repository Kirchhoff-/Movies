package com.kirchhoff.movies.di

import com.kirchhoff.movies.ui.screens.details.movie.MovieDetailsVM
import com.kirchhoff.movies.ui.screens.details.person.PersonDetailsVM
import com.kirchhoff.movies.ui.screens.details.tv.TvDetailsVM
import com.kirchhoff.movies.ui.screens.main.movies.MoviesVM
import com.kirchhoff.movies.ui.screens.main.persons.PersonsVM
import com.kirchhoff.movies.ui.screens.main.tvs.TvsVM
import com.kirchhoff.movies.ui.screens.reviews.list.ReviewsListVM
import com.kirchhoff.movies.ui.screens.similar.movie.SimilarMoviesVM
import com.kirchhoff.movies.ui.screens.similar.tv.SimilarTvsVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MoviesVM(discoverRepository = get()) }
    viewModel { TvsVM(discoverRepository = get()) }
    viewModel { PersonsVM(personRepository = get()) }
    viewModel { MovieDetailsVM(movieRepository = get()) }
    viewModel { TvDetailsVM(tvRepository = get()) }
    viewModel { PersonDetailsVM(personRepository = get()) }
    viewModel { ReviewsListVM(movieRepository = get(), tvRepository = get()) }
    viewModel { (movieId: Int) -> SimilarMoviesVM(movieId, movieRepository = get()) }
    viewModel { (tvId: Int) -> SimilarTvsVM(tvId, tvRepository = get()) }
}
