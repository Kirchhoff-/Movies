package com.kirchhoff.movies.screen.movie.ui.screen.images.usecase

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIImage
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository

internal interface IMovieImagesUseCase {
    suspend fun fetchImages(id: MovieId): kotlin.Result<List<UIImage>>
}

internal class MovieImagesUseCase(private val movieRepository: IMovieRepository) : IMovieImagesUseCase {
    override suspend fun fetchImages(id: MovieId): kotlin.Result<List<UIImage>> =
        when (val response = movieRepository.fetchImages(id)) {
            is Result.Success -> kotlin.Result.success(response.data)
            else -> kotlin.Result.failure(Exception("Can't fetch the images"))
        }
}
