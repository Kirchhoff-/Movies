package com.kirchhoff.movies.screen.person.ui.screen.details

import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.screen.person.ui.screen.details.mapper.IPersonDetailsMapper
import com.kirchhoff.movies.screen.person.ui.screen.details.mapper.PersonDetailsMapper
import com.kirchhoff.movies.screen.person.ui.screen.details.network.PersonDetailsService
import com.kirchhoff.movies.screen.person.ui.screen.details.repository.IPersonDetailsRepository
import com.kirchhoff.movies.screen.person.ui.screen.details.repository.PersonDetailsRepository
import com.kirchhoff.movies.screen.person.ui.screen.details.usecase.IPersonDetailsUseCase
import com.kirchhoff.movies.screen.person.ui.screen.details.usecase.PersonDetailsUseCase
import com.kirchhoff.movies.screen.person.ui.screen.details.viewmodel.PersonDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val personDetailsModule = module {
    single { get<Retrofit>().create(PersonDetailsService::class.java) }

    single<IPersonDetailsRepository> {
        PersonDetailsRepository(personDetailsService = get())
    }

    single<IPersonDetailsMapper> {
        PersonDetailsMapper()
    }

    single<IPersonDetailsUseCase> {
        PersonDetailsUseCase(
            personDetailsRepository = get(),
            personImageStorage = get(),
            personDetailsMapper = get()
        )
    }

    viewModel { (person: UIPerson) ->
        PersonDetailsViewModel(
            person = person,
            personDetailsUseCase = get()
        )
    }
}
