package com.kirchhoff.movies.screen.movie.repository

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.movie.NetworkMovieDetails
import com.kirchhoff.movies.networkdata.details.movie.NetworkTrailersList
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.network.MovieService
import com.kirchhoff.movies.storage.movie.IStorageMovie

internal interface IMovieDetailsRepository {
    suspend fun info(id: MovieId): RepositoryResult<NetworkMovie>
    suspend fun details(id: MovieId): RepositoryResult<NetworkMovieDetails>
    suspend fun trailersList(id: MovieId): RepositoryResult<NetworkTrailersList>
    suspend fun movieCredits(id: MovieId): RepositoryResult<NetworkEntertainmentCredits>
    suspend fun similarMovies(id: MovieId, page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>>
}

internal class MovieDetailsRepository(
    private val movieService: MovieService,
    private val movieStorage: IStorageMovie
) : BaseRepository(), IMovieDetailsRepository {

    override suspend fun info(id: MovieId): RepositoryResult<NetworkMovie> {
        val movieInfo = movieStorage.info(id.value)

        return if (movieInfo != null) {
            RepositoryResult.Success(movieInfo)
        } else {
            RepositoryResult.Exception(Exception("There is no movie with id = $id in the storage"))
        }
    }

    override suspend fun details(id: MovieId): RepositoryResult<NetworkMovieDetails> = apiCall { movieService.fetchDetails(id.value) }

    override suspend fun trailersList(id: MovieId): RepositoryResult<NetworkTrailersList> =
        apiCall { movieService.fetchTrailersList(id.value) }

    override suspend fun movieCredits(id: MovieId): RepositoryResult<NetworkEntertainmentCredits> {
        val result = apiCall { movieService.fetchMovieCredits(id.value) }

        if (result is RepositoryResult.Success) {
            movieStorage.updateCredits(id.value, result.data)
        }

        return result
    }

    override suspend fun similarMovies(id: MovieId, page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> {
        val result = apiCall { movieService.fetchSimilarMovies(id.value, page) }

        if (result is RepositoryResult.Success) {
            result.data.results.forEach { movie -> movieStorage.updateInfo(movie) }
        }

        return result
    }
}
