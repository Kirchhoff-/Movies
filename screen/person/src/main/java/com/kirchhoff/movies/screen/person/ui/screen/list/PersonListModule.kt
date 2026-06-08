package com.kirchhoff.movies.screen.person.ui.screen.list

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.screen.person.router.PersonRouter
import com.kirchhoff.movies.screen.person.storage.PersonImagesStorage
import com.kirchhoff.movies.screen.person.ui.screen.list.mapper.PersonListMapper
import com.kirchhoff.movies.screen.person.ui.screen.list.network.PersonListService
import com.kirchhoff.movies.screen.person.ui.screen.list.repository.PersonListRepository
import com.kirchhoff.movies.screen.person.ui.screen.list.usecase.PersonListUseCase
import com.kirchhoff.movies.screen.person.ui.screen.list.viewmodel.PersonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val personModule = module {
    single { get<Retrofit>().create(PersonListService::class.java) }

    single<PersonRouter> { (activity: AppCompatActivity) ->
        PersonRouter(activity)
    }

    single<PersonListMapper> { PersonListMapper() }

    single<PersonImagesStorage> { PersonImagesStorage() }

    single<PersonListRepository> {
        PersonListRepository(personListService = get())
    }

    single<PersonListUseCase> {
        PersonListUseCase(
            personListRepository = get(),
            personListMapper = get()
        )
    }

    viewModel { PersonListViewModel(personListUseCase = get()) }
}
