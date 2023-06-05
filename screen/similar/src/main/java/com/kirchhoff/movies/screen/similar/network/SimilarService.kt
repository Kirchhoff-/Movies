package com.kirchhoff.movies.screen.similar.network

import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimilarService {
    @GET("/3/movie/{movie_id}/similar")
    suspend fun fetchMovies(
        @Path("movie_id") id: Int,
        @Query("page") page: Int
    ): Response<NetworkPaginated<NetworkMovie>>
}
