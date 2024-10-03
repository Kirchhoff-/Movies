package com.kirchhoff.movies.screen.movie.repository

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.mapper.IMovieDetailsMapper
import com.kirchhoff.movies.screen.movie.network.MovieService
import com.kirchhoff.movies.screen.movie.storage.IMovieImagesStorage
import com.kirchhoff.movies.storage.movie.IStorageMovie

internal interface IMovieRepository {
    suspend fun fetchMovie(movieId: MovieId): Result<NetworkMovie>
    suspend fun fetchImages(id: MovieId): Result<List<UIImage>>
}

internal class MovieRepository(
    private val movieService: MovieService,
    private val movieStorage: IStorageMovie,
    private val movieImagesStorage: IMovieImagesStorage,
    private val movieDetailsMapper: IMovieDetailsMapper
) : BaseRepository(), IMovieRepository {

    override suspend fun fetchMovie(movieId: MovieId): Result<NetworkMovie> {
        val movie = movieStorage.info(movieId.value)

        return if (movie != null) {
            Result.Success(movie)
        } else {
            Result.Exception("There is no movie with id = $movieId in the storage")
        }
    }

    override suspend fun fetchImages(id: MovieId): Result<List<UIImage>> {
        val localImages = movieImagesStorage.fetchImages(id)

        return if (localImages != null) {
            Result.Success(localImages)
        } else {
            val result = apiCall { movieService.fetchImages(id.value) }

            return if (result is Result.Success) {
                val uiImages = movieDetailsMapper.createUIImages(result.data)
                movieImagesStorage.updateImages(id, uiImages)
                Result.Success(uiImages)
            } else {
                result.mapErrorOrException()
            }
        }
    }
}
