package com.kirchhoff.movies.screen.movie.ui.screen.discover.repository

import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.ui.screen.discover.network.MovieDiscoverService
import com.kirchhoff.movies.storage.movie.IStorageMovie
import retrofit2.Response

internal interface IMovieDiscoverRepository {
    suspend fun nowPlaying(): Result<NetworkPaginated<NetworkMovie>>
    suspend fun popular(): Result<NetworkPaginated<NetworkMovie>>
    suspend fun topRated(): Result<NetworkPaginated<NetworkMovie>>
    suspend fun upcoming(): Result<NetworkPaginated<NetworkMovie>>
}

internal class MovieDiscoverRepository(
    private val movieDiscoverService: MovieDiscoverService,
    private val movieStorage: IStorageMovie
) : BaseRepository(), IMovieDiscoverRepository {

    override suspend fun nowPlaying(): Result<NetworkPaginated<NetworkMovie>> =
        fetchMovies { movieDiscoverService.fetchNowPlaying() }

    override suspend fun popular(): Result<NetworkPaginated<NetworkMovie>> =
        fetchMovies { movieDiscoverService.fetchPopular() }

    override suspend fun topRated(): Result<NetworkPaginated<NetworkMovie>> =
        fetchMovies { movieDiscoverService.fetchTopRated() }

    override suspend fun upcoming(): Result<NetworkPaginated<NetworkMovie>> =
        fetchMovies { movieDiscoverService.fetchUpcoming() }

    private suspend fun fetchMovies(call: suspend () -> Response<NetworkPaginated<NetworkMovie>>): Result<NetworkPaginated<NetworkMovie>> {
        val result = apiCall { call.invoke() }

        if (result is Result.Success) {
            result.data.results.forEach { movieStorage.updateInfo(it) }
        }

        return result
    }
}
