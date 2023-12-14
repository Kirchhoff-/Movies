package com.kirchhoff.movies.screen.person.ui.screen.details

import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.screen.person.ui.screen.details.viewmodel.PersonDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val personDetailsModule = module {
    viewModel { (person: UIPerson) ->
        PersonDetailsViewModel(
            person = person,
            personRepository = get()
        )
    }
}
