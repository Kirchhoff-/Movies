package com.kirchhoff.movies.screen.tvshow.network

import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.tv.NetworkTvDetails
import com.kirchhoff.movies.networkdata.main.NetworkTv
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowService {
    @GET("/3/discover/tv?language=en&sort_by=popularity.desc")
    suspend fun fetchDiscoverList(@Query("page") page: Int): Response<NetworkPaginated<NetworkTv>>

    @GET("/3/tv/{tv_id}")
    suspend fun fetchDetails(@Path("tv_id") id: Int): Response<NetworkTvDetails>

    @GET("/3/tv/{tv_id}/credits")
    suspend fun fetchCredits(@Path("tv_id") id: Int): Response<NetworkEntertainmentCredits>
}
