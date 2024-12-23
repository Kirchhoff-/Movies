package com.kirchhoff.movies.screen.movie.repository

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.movie.NetworkMovieDetails
import com.kirchhoff.movies.networkdata.details.movie.NetworkTrailersList
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.network.MovieService
import com.kirchhoff.movies.storage.movie.IStorageMovie

internal interface IMovieDetailsRepository {
    suspend fun fetchInfo(id: MovieId): Result<NetworkMovie>
    suspend fun fetchDetails(id: MovieId): Result<NetworkMovieDetails>
    suspend fun fetchTrailersList(id: MovieId): Result<NetworkTrailersList>
    suspend fun fetchMovieCredits(id: MovieId): Result<NetworkEntertainmentCredits>
    suspend fun fetchSimilarMovies(id: MovieId, page: Int): Result<NetworkPaginated<NetworkMovie>>
}

internal class MovieDetailsRepository(
    private val movieService: MovieService,
    private val movieStorage: IStorageMovie
) : BaseRepository(), IMovieDetailsRepository {

    override suspend fun fetchInfo(id: MovieId): Result<NetworkMovie> {
        val movieInfo = movieStorage.info(id.value)

        return if (movieInfo != null) {
            Result.Success(movieInfo)
        } else {
            Result.Exception(Exception("There is no movie with id = $id in the storage"))
        }
    }

    override suspend fun fetchDetails(id: MovieId): Result<NetworkMovieDetails> = apiCall { movieService.fetchDetails(id.value) }

    override suspend fun fetchTrailersList(id: MovieId): Result<NetworkTrailersList> = apiCall { movieService.fetchTrailersList(id.value) }

    override suspend fun fetchMovieCredits(id: MovieId): Result<NetworkEntertainmentCredits> {
        val result = apiCall { movieService.fetchMovieCredits(id.value) }

        if (result is Result.Success) {
            movieStorage.updateCredits(id.value, result.data)
        }

        return result
    }

    override suspend fun fetchSimilarMovies(id: MovieId, page: Int): Result<NetworkPaginated<NetworkMovie>> {
        val result = apiCall { movieService.fetchSimilarMovies(id.value, page) }

        if (result is Result.Success) {
            result.data.results.forEach { movie -> movieStorage.updateInfo(movie) }
        }

        return result
    }
}
