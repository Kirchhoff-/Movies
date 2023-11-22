package com.kirchhoff.movies.screen.credits.ui.screen.cast

import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.screen.credits.ui.screen.cast.viewmodel.CreditsCastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val creditsCastModule = module {
    viewModel { (actors: List<UIEntertainmentPerson.Actor>) ->
        CreditsCastViewModel(actors = actors)
    }
}
