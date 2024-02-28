package com.kirchhoff.movies.screen.person.ui.screen.images.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirchhoff.movies.screen.person.ui.screen.images.model.PersonImagesScreenState
import com.kirchhoff.movies.screen.person.ui.screen.images.usecase.IPersonImagesUseCase

internal class PersonImagesViewModel(
    personId: Int,
    startPosition: Int,
    personImagesUseCase: IPersonImagesUseCase
) : ViewModel() {

    val screenState: MutableLiveData<PersonImagesScreenState> = MutableLiveData()

    init {
        screenState.value = PersonImagesScreenState(
            imagesUrls = personImagesUseCase.fetchImages(personId),
            startPosition = startPosition
        )
    }
}
