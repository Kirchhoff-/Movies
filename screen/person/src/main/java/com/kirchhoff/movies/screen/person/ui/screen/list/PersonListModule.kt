package com.kirchhoff.movies.screen.person.ui.screen.list

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.screen.person.router.IPersonRouter
import com.kirchhoff.movies.screen.person.router.PersonRouter
import com.kirchhoff.movies.screen.person.storage.IPersonImagesStorage
import com.kirchhoff.movies.screen.person.storage.PersonImagesStorage
import com.kirchhoff.movies.screen.person.ui.screen.list.mapper.IPersonListMapper
import com.kirchhoff.movies.screen.person.ui.screen.list.mapper.PersonListMapper
import com.kirchhoff.movies.screen.person.ui.screen.list.network.PersonListService
import com.kirchhoff.movies.screen.person.ui.screen.list.repository.IPersonListRepository
import com.kirchhoff.movies.screen.person.ui.screen.list.repository.PersonListRepository
import com.kirchhoff.movies.screen.person.ui.screen.list.usecase.IPersonListUseCase
import com.kirchhoff.movies.screen.person.ui.screen.list.usecase.PersonListUseCase
import com.kirchhoff.movies.screen.person.ui.screen.list.viewmodel.PersonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val personModule = module {
    single { get<Retrofit>().create(PersonListService::class.java) }

    single<IPersonRouter> { (activity: AppCompatActivity) ->
        PersonRouter(activity)
    }

    single<IPersonListMapper> { PersonListMapper() }

    single<IPersonImagesStorage> { PersonImagesStorage() }

    single<IPersonListRepository> {
        PersonListRepository(personListService = get())
    }

    single<IPersonListUseCase> {
        PersonListUseCase(
            personListRepository = get(),
            personListMapper = get()
        )
    }

    viewModel { PersonListViewModel(personListUseCase = get()) }
}
