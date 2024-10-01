package com.kirchhoff.movies.screen.person.ui.screen.list.network

import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkPerson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface PersonListService {
    @GET("/3/person/popular?language=en")
    suspend fun fetchPopularPerson(@Query("page") page: Int): Response<NetworkPaginated<NetworkPerson>>
}
