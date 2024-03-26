package com.kirchhoff.movies.screen.movie.ui.screen.discover.network

import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MovieDiscoverService {

    @GET("/3/movie/now_playing")
    suspend fun fetchNowPlaying(@Query("page") page: Int): Response<NetworkPaginated<NetworkMovie>>

    @GET("/3/movie/popular")
    suspend fun fetchPopular(@Query("page") page: Int): Response<NetworkPaginated<NetworkMovie>>

    @GET("/3/movie/top_rated")
    suspend fun fetchTopRated(@Query("page") page: Int): Response<NetworkPaginated<NetworkMovie>>

    @GET("/3/movie/upcoming")
    suspend fun fetchUpcoming(@Query("page") page: Int): Response<NetworkPaginated<NetworkMovie>>
}
