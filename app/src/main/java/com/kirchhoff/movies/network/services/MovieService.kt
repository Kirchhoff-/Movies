package com.kirchhoff.movies.network.services

import com.kirchhoff.movies.data.network.details.movie.NetworkMovieCredits
import com.kirchhoff.movies.data.network.details.movie.NetworkMovieDetails
import com.kirchhoff.movies.data.network.details.movie.NetworkTrailersList
import com.kirchhoff.movies.data.network.details.review.NetworkReviewsListResponse
import com.kirchhoff.movies.data.network.main.NetworkDiscoverMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("/3/movie/{movie_id}")
    suspend fun fetchDetails(@Path("movie_id") id: Int): Response<NetworkMovieDetails>

    @GET("/3/movie/{movie_id}/reviews")
    suspend fun fetchReviews(@Path("movie_id") id: Int, @Query("page") page: Int): Response<NetworkReviewsListResponse>

    @GET("/3/movie/{movie_id}/similar")
    suspend fun fetchSimilarMovies(@Path("movie_id") id: Int, @Query("page") page: Int): Response<NetworkDiscoverMovies>

    @GET("/3/movie/{movie_id}/videos")
    suspend fun fetchTrailersList(@Path("movie_id") id: Int): Response<NetworkTrailersList>

    @GET("/3/movie/{movie_id}/credits")
    suspend fun fetchMovieCredits(@Path("movie_id") id: Int): Response<NetworkMovieCredits>
}
