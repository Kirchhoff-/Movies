package com.kirchhoff.movies.network.services

import com.kirchhoff.movies.data.DiscoverMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverService {
    @GET("/3/discover/movie?language=en&sort_by=popularity.desc")
    suspend fun fetchDiscoverMovieAsync(@Query("page") page: Int): Response<DiscoverMoviesResponse>
}