package com.kirchhoff.movies.network.services

import com.kirchhoff.movies.data.responses.DiscoverTvsResponse
import com.kirchhoff.movies.data.responses.ReviewsListResponse
import com.kirchhoff.movies.data.responses.TvDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvService {
    @GET("/3/tv/{tv_id}")
    suspend fun fetchDetails(@Path("tv_id") id: Int): Response<TvDetails>

    @GET("/3/tv/{tv_id}/similar")
    suspend fun fetchSimilarTv(@Path("tv_id") id: Int, @Query("page") page: Int): Response<DiscoverTvsResponse>

    @GET("/3/tv/{tv_id}/reviews")
    suspend fun fetchReviews(@Path("tv_id") id: Int, @Query("page") page: Int): Response<ReviewsListResponse>
}
