package com.kirchhoff.movies.screen.country.repository

import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.country.network.MoviesByCountryService

class MoviesByCountryRepository(
    private val moviesByCountryService: MoviesByCountryService
) : BaseRepository(), IMoviesByCountryRepository {

    override suspend fun fetchMovies(
        countryId: String,
        page: Int
    ): Result<NetworkPaginated<NetworkMovie>> =
        apiCall { moviesByCountryService.fetchMovies(countryId, page) }
}
