package com.kirchhoff.movies.screen.person.ui.screen.images.usecase

import com.kirchhoff.movies.screen.person.data.UIPersonImage
import com.kirchhoff.movies.screen.person.storage.IPersonImagesStorage

internal interface IPersonImagesUseCase {
    fun fetchImages(personId: Int): List<UIPersonImage>
}

internal class PersonImagesUseCase(private val personImagesStorage: IPersonImagesStorage) : IPersonImagesUseCase {
    override fun fetchImages(personId: Int): List<UIPersonImage> = personImagesStorage.fetchImages(personId) ?: error("There are no images for person with id = $personId")
}
