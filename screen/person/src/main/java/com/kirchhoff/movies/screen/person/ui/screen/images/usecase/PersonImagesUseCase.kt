package com.kirchhoff.movies.screen.person.ui.screen.images.usecase

import com.kirchhoff.movies.screen.person.data.UIPersonImage
import com.kirchhoff.movies.screen.person.storage.PersonImagesStorage

internal class PersonImagesUseCase(private val personImagesStorage: PersonImagesStorage) {
    fun fetchImages(personId: Int): List<UIPersonImage> = personImagesStorage.fetchImages(personId) ?: error(
        "There are no images for person with id = $personId"
    )
}
