package com.kirchhoff.movies.screen.movie.storage

import com.kirchhoff.movies.core.data.UIImage

internal interface IMovieImagesStorage {
    fun fetchImages(movieId: Int): List<UIImage>?
    fun updateImages(movieId: Int, images: List<UIImage>)
}

internal class MovieImagesStorage : IMovieImagesStorage {

    private val imagesMap: MutableMap<Int, List<UIImage>> = mutableMapOf()

    override fun fetchImages(movieId: Int): List<UIImage>? = imagesMap[movieId]

    override fun updateImages(movieId: Int, images: List<UIImage>) {
        if (imagesMap.size > MAX_STORAGE_SIZE) {
            imagesMap.remove(imagesMap.keys.first())
        }
        imagesMap[movieId] = images
    }

    private companion object {
        const val MAX_STORAGE_SIZE = 10
    }
}
