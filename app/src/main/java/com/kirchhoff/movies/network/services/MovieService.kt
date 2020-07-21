package com.kirchhoff.movies.network.services

import com.kirchhoff.movies.data.responses.MovieDetails
import com.kirchhoff.movies.data.responses.ReviewsListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("/3/movie/{movie_id}")
    suspend fun fetchDetails(@Path("movie_id") id: Int): Response<MovieDetails>

    @GET("/3/movie/{movie_id}/reviews")
    suspend fun fetchReviews(@Path("movie_id") id: Int, @Query("page") page: Int): Response<ReviewsListResponse>
}
