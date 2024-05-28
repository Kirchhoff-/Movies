package com.kirchhoff.movies.screen.movie.ui.screen.discover.network

import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MovieDiscoverService {
    @GET("/3/movie/now_playing?page=1")
    suspend fun fetchNowPlaying(): Response<NetworkPaginated<NetworkMovie>>

    @GET("/3/movie/popular?page=1")
    suspend fun fetchPopular(): Response<NetworkPaginated<NetworkMovie>>

    @GET("/3/movie/top_rated?page=1")
    suspend fun fetchTopRated(): Response<NetworkPaginated<NetworkMovie>>

    @GET("/3/movie/upcoming?page=1")
    suspend fun fetchUpcoming(): Response<NetworkPaginated<NetworkMovie>>
}
