package com.kirchhoff.movies.screen.movie.usecase

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIImage
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository

internal interface IMovieUseCase {
    suspend fun fetchImages(id: MovieId): kotlin.Result<List<UIImage>>
}

internal class MovieUseCase(private val movieRepository: IMovieRepository) : IMovieUseCase {
    override suspend fun fetchImages(id: MovieId): kotlin.Result<List<UIImage>> =
        when (val response = movieRepository.images(id)) {
            is Result.Success -> kotlin.Result.success(response.data)
            else -> kotlin.Result.failure(Exception("Can't fetch the images"))
        }
}
