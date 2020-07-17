package com.kirchhoff.movies.network.services

import com.kirchhoff.movies.data.responses.TvDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TvService {
    @GET("/3/tv/{tv_id}")
    suspend fun fetchDetails(@Path("tv_id") id: Int): Response<TvDetails>
}