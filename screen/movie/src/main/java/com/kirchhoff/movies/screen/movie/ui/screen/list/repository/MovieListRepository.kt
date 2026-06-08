package com.kirchhoff.movies.screen.movie.ui.screen.list.repository

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.network.MovieService
import com.kirchhoff.movies.storage.movie.StorageMovie
import retrofit2.Response

internal class MovieListRepository(
    private val movieService: MovieService,
    private val movieStorage: StorageMovie
) : BaseRepository() {

    suspend fun byGenre(genre: String, page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchByGenre(
            genre = genre,
            page = page
        )
    }

    suspend fun byCountry(countryId: String, page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchByCountry(
            countryId = countryId,
            page = page
        )
    }

    suspend fun similar(id: MovieId, page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchSimilarMovies(
            id = id.value,
            page = page
        )
    }

    suspend fun byCompany(companyId: String, page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchByCompany(
            companyId = companyId,
            page = page
        )
    }

    suspend fun nowPlaying(page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchNowPlaying(page)
    }

    suspend fun popular(page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchPopular(page)
    }

    suspend fun topRated(page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchTopRated(page)
    }

    suspend fun upcoming(page: Int): RepositoryResult<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchUpcoming(page)
    }

    private suspend fun fetchMovies(
        call: suspend () -> Response<NetworkPaginated<NetworkMovie>>
    ): RepositoryResult<NetworkPaginated<NetworkMovie>> {
        val result = apiCall { call.invoke() }

        if (result is RepositoryResult.Success) {
            result.data.results.forEach { movieStorage.updateInfo(it) }
        }

        return result
    }
}
