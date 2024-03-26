package com.kirchhoff.movies.screen.movie.ui.screen.discover.repository

import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.ui.screen.discover.network.MovieDiscoverService
import com.kirchhoff.movies.storage.movie.IStorageMovie
import retrofit2.Response

internal interface IMovieDiscoverRepository {
    suspend fun nowPlaying(page: Int): Result<NetworkPaginated<NetworkMovie>>
    suspend fun popular(page: Int): Result<NetworkPaginated<NetworkMovie>>
    suspend fun topRated(page: Int): Result<NetworkPaginated<NetworkMovie>>
    suspend fun upcoming(page: Int): Result<NetworkPaginated<NetworkMovie>>
}

internal class MovieDiscoverRepository(
    private val movieDiscoverService: MovieDiscoverService,
    private val movieStorage: IStorageMovie
) : BaseRepository(), IMovieDiscoverRepository {

    override suspend fun nowPlaying(page: Int): Result<NetworkPaginated<NetworkMovie>> =
        fetchMovies { movieDiscoverService.fetchNowPlaying(page) }

    override suspend fun popular(page: Int): Result<NetworkPaginated<NetworkMovie>> =
        fetchMovies { movieDiscoverService.fetchPopular(page) }

    override suspend fun topRated(page: Int): Result<NetworkPaginated<NetworkMovie>> =
        fetchMovies { movieDiscoverService.fetchTopRated(page) }

    override suspend fun upcoming(page: Int): Result<NetworkPaginated<NetworkMovie>> =
        fetchMovies { movieDiscoverService.fetchUpcoming(page) }

    private suspend fun fetchMovies(call: suspend () -> Response<NetworkPaginated<NetworkMovie>>): Result<NetworkPaginated<NetworkMovie>> {
        val result = apiCall { call.invoke() }

        if (result is Result.Success) {
            result.data.results.forEach { movieStorage.updateInfo(it) }
        }

        return result
    }
}
