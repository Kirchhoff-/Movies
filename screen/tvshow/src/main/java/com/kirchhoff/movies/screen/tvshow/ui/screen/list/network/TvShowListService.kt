package com.kirchhoff.movies.screen.tvshow.ui.screen.list.network

import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkTv
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface TvShowListService {
    @GET("/3/tv/{tv_id}/similar")
    suspend fun fetchSimilarTvShows(
        @Path("tv_id") id: Int,
        @Query("page") page: Int
    ): Response<NetworkPaginated<NetworkTv>>

    @GET("3/tv/airing_today")
    suspend fun fetchAiringToday(@Query("page") page: Int): Response<NetworkPaginated<NetworkTv>>

    @GET("3/tv/on_the_air")
    suspend fun fetchOnTheAir(@Query("page") page: Int): Response<NetworkPaginated<NetworkTv>>

    @GET("3/tv/popular")
    suspend fun fetchPopular(@Query("page") page: Int): Response<NetworkPaginated<NetworkTv>>

    @GET("3/tv/top_rated")
    suspend fun fetchTopRated(@Query("page") page: Int): Response<NetworkPaginated<NetworkTv>>
}
