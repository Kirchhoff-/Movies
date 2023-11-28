package com.kirchhoff.movies.screen.person

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.screen.person.mapper.details.IPersonDetailsMapper
import com.kirchhoff.movies.screen.person.mapper.details.PersonDetailsMapper
import com.kirchhoff.movies.screen.person.mapper.main.IPersonsMapper
import com.kirchhoff.movies.screen.person.mapper.main.PersonsMapper
import com.kirchhoff.movies.screen.person.network.PersonService
import com.kirchhoff.movies.screen.person.repository.IPersonsRepository
import com.kirchhoff.movies.screen.person.repository.PersonsRepository
import com.kirchhoff.movies.screen.person.router.IPersonRouter
import com.kirchhoff.movies.screen.person.router.PersonRouter
import com.kirchhoff.movies.screen.person.ui.screen.details.OldPersonDetailsViewModel
import com.kirchhoff.movies.screen.person.ui.screen.list.viewmodel.PersonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val personModule = module {
    single { get<Retrofit>().create(PersonService::class.java) }

    single<IPersonRouter> { (activity: AppCompatActivity) ->
        PersonRouter(activity)
    }

    single<IPersonDetailsMapper> { PersonDetailsMapper() }

    single<IPersonsMapper> { PersonsMapper() }

    single<IPersonsRepository> {
        PersonsRepository(
            personService = get(),
            personMapper = get(),
            personDetailsMapper = get()
        )
    }

    viewModel { PersonListViewModel(personRepository = get()) }

    viewModel { OldPersonDetailsViewModel(personRepository = get()) }
}
