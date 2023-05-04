package com.kirchhoff.movies.network.services

import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.details.movie.NetworkMovieDetails
import com.kirchhoff.movies.networkdata.details.movie.NetworkTrailersList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("/3/movie/{movie_id}")
    suspend fun fetchDetails(@Path("movie_id") id: Int): Response<NetworkMovieDetails>

    @GET("/3/movie/{movie_id}/videos")
    suspend fun fetchTrailersList(@Path("movie_id") id: Int): Response<NetworkTrailersList>

    @GET("/3/movie/{movie_id}/credits")
    suspend fun fetchMovieCredits(@Path("movie_id") id: Int): Response<NetworkEntertainmentCredits>
}
