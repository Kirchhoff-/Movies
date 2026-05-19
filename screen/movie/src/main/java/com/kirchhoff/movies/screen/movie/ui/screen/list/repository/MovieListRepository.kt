package com.kirchhoff.movies.screen.movie.ui.screen.list.repository

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.network.MovieService
import com.kirchhoff.movies.storage.movie.IStorageMovie
import retrofit2.Response

internal interface IMovieListRepository {
    suspend fun byGenre(genre: String, page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>>
    suspend fun byCountry(countryId: String, page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>>
    suspend fun similar(id: MovieId, page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>>
    suspend fun byCompany(companyId: String, page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>>
    suspend fun nowPlaying(page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>>
    suspend fun popular(page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>>
    suspend fun topRated(page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>>
    suspend fun upcoming(page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>>
}

internal class MovieListRepository(
    private val movieService: MovieService,
    private val movieStorage: IStorageMovie
) : BaseRepository(), IMovieListRepository {

    override suspend fun byGenre(genre: String, page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchByGenre(
            genre = genre,
            page = page
        )
    }

    override suspend fun byCountry(countryId: String, page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchByCountry(
            countryId = countryId,
            page = page
        )
    }

    override suspend fun similar(id: MovieId, page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchSimilarMovies(
            id = id.value,
            page = page
        )
    }

    override suspend fun byCompany(companyId: String, page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchByCompany(
            companyId = companyId,
            page = page
        )
    }

    override suspend fun nowPlaying(page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchNowPlaying(page)
    }

    override suspend fun popular(page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchPopular(page)
    }

    override suspend fun topRated(page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchTopRated(page)
    }

    override suspend fun upcoming(page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchUpcoming(page)
    }

    private suspend fun fetchMovies(call: suspend () -> Response<NetworkPaginated<NetworkMovie>>): RepositoryResult<NetworkPaginated<NetworkMovie>> {
        val result = apiCall { call.invoke() }

        if (result is RepositoryResult.Success) {
            result.data.results.forEach { movieStorage.updateInfo(it) }
        }

        return result
    }
}
