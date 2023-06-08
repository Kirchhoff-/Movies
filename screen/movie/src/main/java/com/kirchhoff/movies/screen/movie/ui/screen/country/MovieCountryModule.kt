package com.kirchhoff.movies.screen.movie.ui.screen.country

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieCountryModule = module {
    viewModel { (countryId: String) ->
        MovieCountryViewModel(
            countryId = countryId,
            movieRepository = get()
        )
    }
}
