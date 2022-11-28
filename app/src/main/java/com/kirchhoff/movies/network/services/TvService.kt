package com.kirchhoff.movies.network.services

import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.review.NetworkReview
import com.kirchhoff.movies.networkdata.details.tv.NetworkTvDetails
import com.kirchhoff.movies.networkdata.main.NetworkTv
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvService {
    @GET("/3/tv/{tv_id}")
    suspend fun fetchDetails(@Path("tv_id") id: Int): Response<NetworkTvDetails>

    @GET("/3/tv/{tv_id}/similar")
    suspend fun fetchSimilarTv(@Path("tv_id") id: Int, @Query("page") page: Int): Response<NetworkPaginated<NetworkTv>>

    @GET("/3/tv/{tv_id}/reviews")
    suspend fun fetchReviews(@Path("tv_id") id: Int, @Query("page") page: Int): Response<NetworkPaginated<NetworkReview>>

    @GET("/3/tv/{tv_id}/credits")
    suspend fun fetchTvCredits(@Path("tv_id") id: Int): Response<NetworkEntertainmentCredits>
}
