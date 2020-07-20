package com.kirchhoff.movies.network.services

import com.kirchhoff.movies.data.responses.PersonDetails
import com.kirchhoff.movies.data.responses.PersonsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonService {
    @GET("/3/person/popular?language=en")
    suspend fun fetchPopularPerson(@Query("page") page: Int): Response<PersonsResponse>

    @GET("/3/person/{person_id}")
    suspend fun fetchPersonDetail(@Path("person_id") personId: Int): Response<PersonDetails>
}
