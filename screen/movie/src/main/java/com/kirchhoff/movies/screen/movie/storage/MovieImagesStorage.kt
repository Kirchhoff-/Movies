package com.kirchhoff.movies.screen.movie.storage

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIImage

internal class MovieImagesStorage {

    private val imagesMap: MutableMap<MovieId, List<UIImage>> = mutableMapOf()

    fun fetchImages(id: MovieId): List<UIImage>? = imagesMap[id]

    fun updateImages(id: MovieId, images: List<UIImage>) {
        if (imagesMap.size > MAX_STORAGE_SIZE) {
            imagesMap.remove(imagesMap.keys.first())
        }
        imagesMap[id] = images
    }

    private companion object {
        const val MAX_STORAGE_SIZE = 10
    }
}
