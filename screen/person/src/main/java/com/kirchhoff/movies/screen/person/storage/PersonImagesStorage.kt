package com.kirchhoff.movies.screen.person.storage

import com.kirchhoff.movies.screen.person.data.PersonUIImage

internal class PersonImagesStorage {

    private val imagesMap: MutableMap<Int, List<PersonUIImage>> = mutableMapOf()

    fun fetchImages(id: Int): List<PersonUIImage>? = imagesMap[id]

    fun updateImages(id: Int, images: List<PersonUIImage>) {
        if (imagesMap.size > MAX_STORAGE_SIZE) {
            imagesMap.remove(imagesMap.keys.first())
        }
        imagesMap[id] = images
    }

    private companion object {
        const val MAX_STORAGE_SIZE = 10
    }
}
