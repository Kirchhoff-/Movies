package com.kirchhoff.movies.screen.credits.ui.screen.crew

import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.screen.credits.ui.screen.crew.viewmodel.CreditsCrewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val creditsCrewModule = module {
    viewModel { (creators: List<UIEntertainmentPerson.Creator>) ->
        CreditsCrewViewModel(creators = creators)
    }
}
