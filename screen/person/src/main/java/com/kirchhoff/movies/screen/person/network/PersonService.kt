package com.kirchhoff.movies.screen.person.network

import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCredits
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonDetails
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonImages
import com.kirchhoff.movies.networkdata.main.NetworkPerson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PersonService {
    @GET("/3/person/popular?language=en")
    suspend fun fetchPopularPerson(@Query("page") page: Int): Response<NetworkPaginated<NetworkPerson>>

    @GET("/3/person/{person_id}")
    suspend fun fetchPersonDetail(@Path("person_id") personId: Int): Response<NetworkPersonDetails>

    @GET("/3/person/{person_id}/combined_credits")
    suspend fun fetchPersonCredits(@Path("person_id") personId: Int): Response<NetworkPersonCredits>

    @GET("/3/person/{person_id}/images")
    suspend fun fetchPersonImages(@Path("person_id") personId: Int): Response<NetworkPersonImages>
}
