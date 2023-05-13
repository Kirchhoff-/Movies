package com.kirchhoff.movies.screen.person

import com.kirchhoff.movies.screen.person.mapper.details.IPersonDetailsMapper
import com.kirchhoff.movies.screen.person.mapper.details.PersonDetailsMapper
import com.kirchhoff.movies.screen.person.mapper.main.IPersonsMapper
import com.kirchhoff.movies.screen.person.mapper.main.PersonsMapper
import com.kirchhoff.movies.screen.person.network.PersonService
import com.kirchhoff.movies.screen.person.repository.IPersonsRepository
import com.kirchhoff.movies.screen.person.repository.PersonsRepository
import com.kirchhoff.movies.screen.person.ui.screen.details.PersonDetailsVM
import com.kirchhoff.movies.screen.person.ui.screen.list.PersonsVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val personModule = module {
    single { get<Retrofit>().create(PersonService::class.java) }

    single<IPersonDetailsMapper> { PersonDetailsMapper() }

    single<IPersonsMapper> { PersonsMapper() }

    single<IPersonsRepository> {
        PersonsRepository(
            personService = get(),
            personMapper = get(),
            personDetailsMapper = get()
        )
    }

    viewModel { PersonsVM(personRepository = get()) }

    viewModel { PersonDetailsVM(personRepository = get()) }
}
