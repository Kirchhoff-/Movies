package com.kirchhoff.movies.screen.movie.ui.screen.images.usecase

import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository

internal interface IMovieImagesUseCase {
    suspend fun fetchImages(movieId: Int): Result<List<UIImage>>
}

internal class MovieImagesUseCase(
    private val movieRepository: IMovieRepository
) : IMovieImagesUseCase {
    override suspend fun fetchImages(movieId: Int): Result<List<UIImage>> =
        movieRepository.fetchImages(movieId)
}
