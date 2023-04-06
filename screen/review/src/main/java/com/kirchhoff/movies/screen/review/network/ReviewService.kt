package com.kirchhoff.movies.screen.review.network

import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.review.NetworkReview
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface ReviewService {
    @GET("/3/movie/{movie_id}/reviews")
    suspend fun movieReviews(
        @Path("movie_id") id: Int,
        @Query("page") page: Int
    ): Response<NetworkPaginated<NetworkReview>>

    @GET("/3/tv/{tv_id}/reviews")
    suspend fun tvReviews(
        @Path("tv_id") id: Int,
        @Query("page") page: Int
    ): Response<NetworkPaginated<NetworkReview>>
}
