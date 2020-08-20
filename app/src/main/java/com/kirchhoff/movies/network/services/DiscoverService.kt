package com.kirchhoff.movies.network.services

import com.kirchhoff.movies.data.network.core.NetworkPaginated
import com.kirchhoff.movies.data.network.main.NetworkMovie
import com.kirchhoff.movies.data.network.main.NetworkTv
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverService {
    @GET("/3/discover/movie?language=en&sort_by=popularity.desc")
    suspend fun fetchDiscoverMovie(@Query("page") page: Int): Response<NetworkPaginated<NetworkMovie>>

    @GET("/3/discover/tv?language=en&sort_by=popularity.desc")
    suspend fun fetchDiscoverTv(@Query("page") page: Int): Response<NetworkPaginated<NetworkTv>>
}
