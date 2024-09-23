package com.kirchhoff.movies.screen.movie.repository

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.screen.movie.mapper.IMovieDetailsMapper
import com.kirchhoff.movies.screen.movie.network.MovieService
import com.kirchhoff.movies.screen.movie.storage.IMovieImagesStorage

internal interface IMovieRepository {
    suspend fun fetchImages(id: MovieId): Result<List<UIImage>>
}

internal class MovieRepository(
    private val movieService: MovieService,
    private val movieImagesStorage: IMovieImagesStorage,
    private val movieDetailsMapper: IMovieDetailsMapper
) : BaseRepository(), IMovieRepository {

    override suspend fun fetchImages(id: MovieId): Result<List<UIImage>> {
        val localImages = movieImagesStorage.fetchImages(id)

        return if (localImages != null) {
            Result.Success(localImages)
        } else {
            val result = movieDetailsMapper.createUIImages(
                apiCall {
                    movieService.fetchImages(id.value)
                }
            )

            if (result is Result.Success) {
                movieImagesStorage.updateImages(id, result.data)
            }

            result
        }
    }
}
