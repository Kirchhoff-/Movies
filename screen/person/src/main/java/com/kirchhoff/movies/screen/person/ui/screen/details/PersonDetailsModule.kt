package com.kirchhoff.movies.screen.person.ui.screen.details

import com.kirchhoff.movies.screen.person.ui.screen.details.viewmodel.PersonDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val personDetailsModule = module {
    viewModel { (personId: Int) ->
        PersonDetailsViewModel(
            personId = personId,
            personRepository = get()
        )
    }
}
