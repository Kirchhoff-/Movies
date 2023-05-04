package com.kirchhoff.movies.network.services

import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.details.tv.NetworkTvDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TvService {
    @GET("/3/tv/{tv_id}")
    suspend fun fetchDetails(@Path("tv_id") id: Int): Response<NetworkTvDetails>

    @GET("/3/tv/{tv_id}/credits")
    suspend fun fetchTvCredits(@Path("tv_id") id: Int): Response<NetworkEntertainmentCredits>
}
