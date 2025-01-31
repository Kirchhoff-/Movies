package com.kirchhoff.movies.screen.movie.storage

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIImage

internal interface IMovieImagesStorage {
    fun fetchImages(id: MovieId): List<UIImage>?
    fun updateImages(id: MovieId, images: List<UIImage>)
}

internal class MovieImagesStorage : IMovieImagesStorage {

    private val imagesMap: MutableMap<MovieId, List<UIImage>> = mutableMapOf()

    override fun fetchImages(id: MovieId): List<UIImage>? = imagesMap[id]

    override fun updateImages(id: MovieId, images: List<UIImage>) {
        if (imagesMap.size > MAX_STORAGE_SIZE) {
            imagesMap.remove(imagesMap.keys.first())
        }
        imagesMap[id] = images
    }

    private companion object {
        const val MAX_STORAGE_SIZE = 10
    }
}
