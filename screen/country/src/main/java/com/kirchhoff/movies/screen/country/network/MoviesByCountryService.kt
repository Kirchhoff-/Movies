package com.kirchhoff.movies.screen.country.network

import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesByCountryService {
    @GET("/3/discover/movie?language=en&sort_by=popularity.desc")
    suspend fun fetchMovies(
        @Query("with_origin_country") countryId: String,
        @Query("page") page: Int
    ): Response<NetworkPaginated<NetworkMovie>>
}
