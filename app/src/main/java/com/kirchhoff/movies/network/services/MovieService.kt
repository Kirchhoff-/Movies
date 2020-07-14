package com.kirchhoff.movies.network.services

import com.kirchhoff.movies.data.responses.MovieDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("/3/movie/{movie_id}")
    fun fetchDetails(@Path("movie_id") id: Int): Response<MovieDetails>
}