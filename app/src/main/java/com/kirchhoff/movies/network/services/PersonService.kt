package com.kirchhoff.movies.network.services

import com.kirchhoff.movies.data.PersonsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonService {
    @GET("/3/person/popular?language=en")
    suspend fun fetchPopularPerson(@Query("page") page: Int): Response<PersonsResponse>
}
