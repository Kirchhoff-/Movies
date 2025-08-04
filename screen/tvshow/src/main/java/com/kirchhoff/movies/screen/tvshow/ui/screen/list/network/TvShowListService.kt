package com.kirchhoff.movies.screen.tvshow.ui.screen.list.network

import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkTv
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface TvShowListService {
    @GET("tv/{tv_id}/similar")
    suspend fun fetchSimilarTvShows(
        @Path("tv_id") id: Int,
        @Query("page") page: Int
    ): Response<NetworkPaginated<NetworkTv>>

    @GET("tv/airing_today")
    suspend fun fetchAiringToday(@Query("page") page: Int): Response<NetworkPaginated<NetworkTv>>

    @GET("tv/on_the_air")
    suspend fun fetchOnTheAir(@Query("page") page: Int): Response<NetworkPaginated<NetworkTv>>

    @GET("tv/popular")
    suspend fun fetchPopular(@Query("page") page: Int): Response<NetworkPaginated<NetworkTv>>

    @GET("tv/top_rated")
    suspend fun fetchTopRated(@Query("page") page: Int): Response<NetworkPaginated<NetworkTv>>
}
