package com.kirchhoff.movies.screen.person.ui.screen.details

import com.kirchhoff.movies.screen.person.ui.screen.details.mapper.PersonDetailsMapper
import com.kirchhoff.movies.screen.person.ui.screen.details.network.PersonDetailsService
import com.kirchhoff.movies.screen.person.ui.screen.details.repository.PersonDetailsRepository
import com.kirchhoff.movies.screen.person.ui.screen.details.usecase.PersonDetailsUseCase
import com.kirchhoff.movies.screen.person.ui.screen.details.viewmodel.PersonDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val personDetailsModule = module {
    single { get<Retrofit>().create(PersonDetailsService::class.java) }

    single<PersonDetailsRepository> {
        PersonDetailsRepository(personDetailsService = get())
    }

    single<PersonDetailsMapper> {
        PersonDetailsMapper()
    }

    single<PersonDetailsUseCase> {
        PersonDetailsUseCase(
            personDetailsRepository = get(),
            personImageStorage = get(),
            personDetailsMapper = get()
        )
    }

    viewModel { (personId: Int) ->
        PersonDetailsViewModel(
            personId = personId,
            personDetailsUseCase = get()
        )
    }
}
