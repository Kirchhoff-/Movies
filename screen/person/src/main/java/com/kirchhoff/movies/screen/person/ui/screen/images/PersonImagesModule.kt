package com.kirchhoff.movies.screen.person.ui.screen.images

import com.kirchhoff.movies.screen.person.ui.screen.images.usecase.IPersonImagesUseCase
import com.kirchhoff.movies.screen.person.ui.screen.images.usecase.PersonImagesUseCase
import com.kirchhoff.movies.screen.person.ui.screen.images.viewmodel.PersonImagesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val personImagesModule = module {
    single<IPersonImagesUseCase> { PersonImagesUseCase(personImagesStorage = get()) }

    viewModel { (personId: Int, startPosition: Int) ->
        PersonImagesViewModel(
            personId = personId,
            startPosition = startPosition,
            personImagesUseCase = get()
        )
    }
}
