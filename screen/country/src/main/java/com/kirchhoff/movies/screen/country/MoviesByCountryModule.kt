package com.kirchhoff.movies.screen.country

import com.kirchhoff.movies.screen.country.network.MoviesByCountryService
import com.kirchhoff.movies.screen.country.repository.IMoviesByCountryRepository
import com.kirchhoff.movies.screen.country.repository.MoviesByCountryRepository
import com.kirchhoff.movies.screen.country.ui.screen.movie.viewmodel.MoviesByCountryViewModel
import com.kirchhoff.movies.screen.country.usecase.IMoviesByCountryUseCase
import com.kirchhoff.movies.screen.country.usecase.MoviesByCountryUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val moviesByCountryModule = module {
    single { get<Retrofit>().create(MoviesByCountryService::class.java) }

    single<IMoviesByCountryRepository> {
        MoviesByCountryRepository(moviesByCountryService = get())
    }

    single<IMoviesByCountryUseCase> {
        MoviesByCountryUseCase(
            moviesByCountryRepository = get(),
            discoverMapper = get()
        )
    }

    viewModel { (countryId: String) ->
        MoviesByCountryViewModel(
            countryId = countryId,
            moviesByCountryUseCase = get()
        )
    }
}
