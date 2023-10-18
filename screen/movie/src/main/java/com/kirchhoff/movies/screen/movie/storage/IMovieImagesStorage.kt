package com.kirchhoff.movies.screen.movie.storage

import com.kirchhoff.movies.core.data.UIImage

internal interface IMovieImagesStorage {
    fun fetchImages(movieId: Int): List<UIImage>?
    fun updateImages(movieId: Int, images: List<UIImage>)
}
