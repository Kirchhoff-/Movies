package com.kirchhoff.movies.screen.movie.repository

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIImage
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.mapper.IMovieDetailsMapper
import com.kirchhoff.movies.screen.movie.network.MovieService
import com.kirchhoff.movies.screen.movie.storage.IMovieImagesStorage
import com.kirchhoff.movies.storage.movie.IStorageMovie

internal interface IMovieRepository {
    suspend fun info(movieId: MovieId): RepositoryResult<NetworkMovie>
    suspend fun images(id: MovieId): RepositoryResult<List<UIImage>>
}

internal class MovieRepository(
    private val movieService: MovieService,
    private val movieStorage: IStorageMovie,
    private val movieImagesStorage: IMovieImagesStorage,
    private val movieDetailsMapper: IMovieDetailsMapper
) : BaseRepository(), IMovieRepository {

    override suspend fun info(movieId: MovieId): RepositoryResult<NetworkMovie> {
        val movie = movieStorage.info(movieId.value)

        return if (movie != null) {
            RepositoryResult.Success(movie)
        } else {
            RepositoryResult.Exception("There is no movie with id = $movieId in the storage")
        }
    }

    override suspend fun images(id: MovieId): RepositoryResult<List<UIImage>> {
        val localImages = movieImagesStorage.fetchImages(id)

        return if (localImages != null) {
            RepositoryResult.Success(localImages)
        } else {
            val result = apiCall { movieService.fetchImages(id.value) }

            return if (result is RepositoryResult.Success) {
                val uiImages = movieDetailsMapper.createUIImages(result.data)
                movieImagesStorage.updateImages(id, uiImages)
                RepositoryResult.Success(uiImages)
            } else {
                result.mapErrorOrException()
            }
        }
    }
}
