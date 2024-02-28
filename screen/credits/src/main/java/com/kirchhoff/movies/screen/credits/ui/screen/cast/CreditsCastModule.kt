package com.kirchhoff.movies.screen.credits.ui.screen.cast

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.screen.credits.ui.screen.cast.usecase.CreditsCastUseCase
import com.kirchhoff.movies.screen.credits.ui.screen.cast.usecase.ICreditsCastUseCase
import com.kirchhoff.movies.screen.credits.ui.screen.cast.viewmodel.CreditsCastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val creditsCastModule = module {
    single<ICreditsCastUseCase> {
        CreditsCastUseCase(
            movieStorage = get(),
            coreMapper = get()
        )
    }

    viewModel { (movieId: MovieId) ->
        CreditsCastViewModel(
            movieId = movieId,
            creditsCastUseCase = get()
        )
    }
}
