package com.kirchhoff.movies.screen.credits.ui.screen.crew

import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.screen.credits.ui.screen.crew.factory.CreditsCrewListFactory
import com.kirchhoff.movies.screen.credits.ui.screen.crew.factory.ICreditsCrewListFactory
import com.kirchhoff.movies.screen.credits.ui.screen.crew.usecase.CreditsCrewUseCase
import com.kirchhoff.movies.screen.credits.ui.screen.crew.usecase.ICreditsCrewUseCase
import com.kirchhoff.movies.screen.credits.ui.screen.crew.viewmodel.CreditsCrewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val creditsCrewModule = module {
    single<ICreditsCrewListFactory> { CreditsCrewListFactory() }

    single<ICreditsCrewUseCase> { CreditsCrewUseCase(creditsCrewListFactory = get()) }

    viewModel { (creators: List<UIEntertainmentPerson.Creator>) ->
        CreditsCrewViewModel(
            creditsCrewUseCase = get(),
            creators = creators
        )
    }
}
