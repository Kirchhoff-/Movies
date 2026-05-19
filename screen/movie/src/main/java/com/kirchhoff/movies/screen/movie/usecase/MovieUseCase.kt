package com.kirchhoff.movies.screen.movie.usecase

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIImage
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository

internal interface IMovieUseCase {
    suspend fun fetchImages(id: MovieId): Result<List<UIImage>>
}

internal class MovieUseCase(private val movieRepository: IMovieRepository) : IMovieUseCase {
    override suspend fun fetchImages(id: MovieId): Result<List<UIImage>> =
        when (val response = movieRepository.images(id)) {
            is RepositoryResult.Success -> Result.success(response.data)
            else -> Result.failure(Exception("Can't fetch the images"))
        }
}
