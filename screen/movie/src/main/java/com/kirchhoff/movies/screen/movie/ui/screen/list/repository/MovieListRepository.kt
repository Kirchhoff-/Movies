package com.kirchhoff.movies.screen.movie.ui.screen.list.repository

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.network.MovieService
import com.kirchhoff.movies.storage.movie.IStorageMovie
import retrofit2.Response

internal interface IMovieListRepository {
    suspend fun fetchByGenre(genre: String, page: Int): Result<NetworkPaginated<NetworkMovie>>
    suspend fun fetchByCountry(countryId: String, page: Int): Result<NetworkPaginated<NetworkMovie>>
    suspend fun fetchSimilar(id: MovieId, page: Int): Result<NetworkPaginated<NetworkMovie>>
    suspend fun fetchByCompany(companyId: String, page: Int): Result<NetworkPaginated<NetworkMovie>>
    suspend fun fetchNowPlaying(page: Int): Result<NetworkPaginated<NetworkMovie>>
    suspend fun fetchPopular(page: Int): Result<NetworkPaginated<NetworkMovie>>
    suspend fun fetchTopRated(page: Int): Result<NetworkPaginated<NetworkMovie>>
    suspend fun fetchUpcoming(page: Int): Result<NetworkPaginated<NetworkMovie>>
}

internal class MovieListRepository(
    private val movieService: MovieService,
    private val movieStorage: IStorageMovie
) : BaseRepository(), IMovieListRepository {

    override suspend fun fetchByGenre(genre: String, page: Int): Result<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchByGenre(
            genre = genre,
            page = page
        )
    }

    override suspend fun fetchByCountry(countryId: String, page: Int): Result<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchByCountry(
            countryId = countryId,
            page = page
        )
    }

    override suspend fun fetchSimilar(id: MovieId, page: Int): Result<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchSimilarMovies(
            id = id.value,
            page = page
        )
    }

    override suspend fun fetchByCompany(companyId: String, page: Int): Result<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchByCompany(
            companyId = companyId,
            page = page
        )
    }

    override suspend fun fetchNowPlaying(page: Int): Result<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchNowPlaying(page)
    }

    override suspend fun fetchPopular(page: Int): Result<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchPopular(page)
    }

    override suspend fun fetchTopRated(page: Int): Result<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchTopRated(page)
    }

    override suspend fun fetchUpcoming(page: Int): Result<NetworkPaginated<NetworkMovie>> = fetchMovies {
        movieService.fetchUpcoming(page)
    }

    private suspend fun fetchMovies(call: suspend () -> Response<NetworkPaginated<NetworkMovie>>): Result<NetworkPaginated<NetworkMovie>> {
        val result = apiCall { call.invoke() }

        if (result is Result.Success) {
            result.data.results.forEach { movieStorage.updateInfo(it) }
        }

        return result
    }
}
