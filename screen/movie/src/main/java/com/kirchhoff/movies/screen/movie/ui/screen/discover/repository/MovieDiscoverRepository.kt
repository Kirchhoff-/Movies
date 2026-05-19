package com.kirchhoff.movies.screen.movie.ui.screen.discover.repository

import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.ui.screen.discover.network.MovieDiscoverService
import com.kirchhoff.movies.storage.movie.IStorageMovie
import retrofit2.Response

internal interface IMovieDiscoverRepository {
    suspend fun nowPlaying(): RepositoryResult<NetworkPaginated<NetworkMovie>>
    suspend fun popular(): RepositoryResult<NetworkPaginated<NetworkMovie>>
    suspend fun topRated(): RepositoryResult<NetworkPaginated<NetworkMovie>>
    suspend fun upcoming(): RepositoryResult<NetworkPaginated<NetworkMovie>>
}

internal class MovieDiscoverRepository(
    private val movieDiscoverService: MovieDiscoverService,
    private val movieStorage: IStorageMovie
) : BaseRepository(), IMovieDiscoverRepository {

    override suspend fun nowPlaying(): RepositoryResult<NetworkPaginated<NetworkMovie>> =
        fetchMovies { movieDiscoverService.fetchNowPlaying() }

    override suspend fun popular(): RepositoryResult<NetworkPaginated<NetworkMovie>> =
        fetchMovies { movieDiscoverService.fetchPopular() }

    override suspend fun topRated(): RepositoryResult<NetworkPaginated<NetworkMovie>> =
        fetchMovies { movieDiscoverService.fetchTopRated() }

    override suspend fun upcoming(): RepositoryResult<NetworkPaginated<NetworkMovie>> =
        fetchMovies { movieDiscoverService.fetchUpcoming() }

    private suspend fun fetchMovies(call: suspend () -> Response<NetworkPaginated<NetworkMovie>>): RepositoryResult<NetworkPaginated<NetworkMovie>> {
        val result = apiCall { call.invoke() }

        if (result is RepositoryResult.Success) {
            result.data.results.forEach { movieStorage.updateInfo(it) }
        }

        return result
    }
}
