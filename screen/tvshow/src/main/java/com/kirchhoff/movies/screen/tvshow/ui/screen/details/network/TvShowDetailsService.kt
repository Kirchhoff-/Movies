package com.kirchhoff.movies.screen.tvshow.ui.screen.details.network

import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.tv.NetworkTvDetails
import com.kirchhoff.movies.networkdata.main.NetworkTv
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface TvShowDetailsService {

    @GET("/3/tv/{tv_id}/similar")
    suspend fun fetchSimilarTvShows(
        @Path("tv_id") id: Int,
        @Query("page") page: Int
    ): Response<NetworkPaginated<NetworkTv>>

    @GET("/3/tv/{tv_id}")
    suspend fun fetchDetails(@Path("tv_id") id: Int): Response<NetworkTvDetails>

    @GET("/3/tv/{tv_id}/credits")
    suspend fun fetchCredits(@Path("tv_id") id: Int): Response<NetworkEntertainmentCredits>
}
