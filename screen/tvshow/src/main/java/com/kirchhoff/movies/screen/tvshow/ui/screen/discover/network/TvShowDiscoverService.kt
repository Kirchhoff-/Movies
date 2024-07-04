package com.kirchhoff.movies.screen.tvshow.ui.screen.discover.network

import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkTv
import retrofit2.Response
import retrofit2.http.GET

internal interface TvShowDiscoverService {
    @GET("3/tv/airing_today?page=1")
    suspend fun fetchAiringToday(): Response<NetworkPaginated<NetworkTv>>

    @GET("3/tv/on_the_air?page=1")
    suspend fun fetchOnTheAir(): Response<NetworkPaginated<NetworkTv>>

    @GET("3/tv/popular?page=1")
    suspend fun fetchPopular(): Response<NetworkPaginated<NetworkTv>>

    @GET("3/tv/top_rated?page=1")
    suspend fun fetchTopRated(): Response<NetworkPaginated<NetworkTv>>
}
