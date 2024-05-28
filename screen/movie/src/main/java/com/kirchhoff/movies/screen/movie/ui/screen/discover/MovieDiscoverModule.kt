package com.kirchhoff.movies.screen.movie.ui.screen.discover

import com.kirchhoff.movies.screen.movie.ui.screen.discover.mapper.IMovieDiscoverMapper
import com.kirchhoff.movies.screen.movie.ui.screen.discover.mapper.MovieDiscoverMapper
import com.kirchhoff.movies.screen.movie.ui.screen.discover.network.MovieDiscoverService
import com.kirchhoff.movies.screen.movie.ui.screen.discover.repository.IMovieDiscoverRepository
import com.kirchhoff.movies.screen.movie.ui.screen.discover.repository.MovieDiscoverRepository
import com.kirchhoff.movies.screen.movie.ui.screen.discover.usecase.IMovieDiscoverUseCase
import com.kirchhoff.movies.screen.movie.ui.screen.discover.usecase.MovieDiscoverUseCase
import com.kirchhoff.movies.screen.movie.ui.screen.discover.viewmodel.MovieDiscoverViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val movieDiscoverModule = module {
    single { get<Retrofit>().create(MovieDiscoverService::class.java) }

    single<IMovieDiscoverMapper> { MovieDiscoverMapper() }

    single<IMovieDiscoverRepository> {
        MovieDiscoverRepository(
            movieDiscoverService = get(),
            movieStorage = get()
        )
    }

    single<IMovieDiscoverUseCase> {
        MovieDiscoverUseCase(
            movieDiscoverRepository = get(),
            movieDiscoverMapper = get()
        )
    }

    viewModel { MovieDiscoverViewModel(movieDiscoverUseCase = get()) }
}
