package com.kirchhoff.movies.screen.movie

import com.kirchhoff.movies.screen.movie.mapper.details.IMovieDetailsMapper
import com.kirchhoff.movies.screen.movie.mapper.details.MovieDetailsMapper
import com.kirchhoff.movies.screen.movie.network.MovieService
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository
import com.kirchhoff.movies.screen.movie.repository.MovieRepository
import com.kirchhoff.movies.screen.movie.ui.screen.list.MovieListVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val movieModule = module {
    single { get<Retrofit>().create(MovieService::class.java) }

    single<IMovieDetailsMapper> { MovieDetailsMapper(coreMapper = get()) }

    single<IMovieRepository> {
        MovieRepository(
            movieService = get(),
            movieDetailsMapper = get(),
            discoverMapper = get()
        )
    }

    viewModel { MovieListVM(movieRepository = get()) }
}