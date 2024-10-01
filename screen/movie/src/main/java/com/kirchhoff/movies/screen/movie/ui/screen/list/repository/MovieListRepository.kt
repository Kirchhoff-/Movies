package com.kirchhoff.movies.screen.movie.ui.screen.list.repository

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.network.MovieService

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

internal class MovieListRepository(private val movieService: MovieService) : BaseRepository(), IMovieListRepository {

    override suspend fun fetchByGenre(genre: String, page: Int): Result<NetworkPaginated<NetworkMovie>> = apiCall {
        movieService.fetchByGenre(
            genre = genre,
            page = page
        )
    }

    override suspend fun fetchByCountry(countryId: String, page: Int): Result<NetworkPaginated<NetworkMovie>> = apiCall {
        movieService.fetchByCountry(
            countryId = countryId,
            page = page
        )
    }

    override suspend fun fetchSimilar(id: MovieId, page: Int): Result<NetworkPaginated<NetworkMovie>> = apiCall {
        movieService.fetchSimilarMovies(
            id = id.value,
            page = page
        )
    }

    override suspend fun fetchByCompany(companyId: String, page: Int): Result<NetworkPaginated<NetworkMovie>> = apiCall {
        movieService.fetchByCompany(
            companyId = companyId,
            page = page
        )
    }

    override suspend fun fetchNowPlaying(page: Int): Result<NetworkPaginated<NetworkMovie>> = apiCall {
        movieService.fetchNowPlaying(page)
    }

    override suspend fun fetchPopular(page: Int): Result<NetworkPaginated<NetworkMovie>> = apiCall {
        movieService.fetchPopular(page)
    }

    override suspend fun fetchTopRated(page: Int): Result<NetworkPaginated<NetworkMovie>> = apiCall {
        movieService.fetchTopRated(page)
    }

    override suspend fun fetchUpcoming(page: Int): Result<NetworkPaginated<NetworkMovie>> = apiCall {
        movieService.fetchUpcoming(page)
    }
}
