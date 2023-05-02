package com.kirchhoff.movies.screen.country.repository

import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie

interface IMoviesByCountryRepository {
    suspend fun fetchMovies(countryId: String, page: Int): Result<NetworkPaginated<NetworkMovie>>
}
