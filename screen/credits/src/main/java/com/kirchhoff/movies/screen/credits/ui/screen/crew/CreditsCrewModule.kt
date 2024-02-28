package com.kirchhoff.movies.screen.credits.ui.screen.crew

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.screen.credits.ui.screen.crew.factory.CreditsCrewListFactory
import com.kirchhoff.movies.screen.credits.ui.screen.crew.factory.ICreditsCrewListFactory
import com.kirchhoff.movies.screen.credits.ui.screen.crew.usecase.CreditsCrewUseCase
import com.kirchhoff.movies.screen.credits.ui.screen.crew.usecase.ICreditsCrewUseCase
import com.kirchhoff.movies.screen.credits.ui.screen.crew.viewmodel.CreditsCrewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val creditsCrewModule = module {
    single<ICreditsCrewListFactory> { CreditsCrewListFactory() }

    single<ICreditsCrewUseCase> {
        CreditsCrewUseCase(
            movieStorage = get(),
            coreMapper = get(),
            creditsCrewListFactory = get()
        )
    }

    viewModel { (movieId: MovieId) ->
        CreditsCrewViewModel(
            movieId = movieId,
            creditsCrewUseCase = get()
        )
    }
}
