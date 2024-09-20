package com.kirchhoff.movies.screen.person.ui.screen.details.network

import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCredits
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonDetails
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonImages
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PersonDetailsService {
    @GET("/3/person/{person_id}")
    suspend fun fetchPersonDetail(@Path("person_id") personId: Int): Response<NetworkPersonDetails>

    @GET("/3/person/{person_id}/combined_credits")
    suspend fun fetchPersonCredits(@Path("person_id") personId: Int): Response<NetworkPersonCredits>

    @GET("/3/person/{person_id}/images")
    suspend fun fetchPersonImages(@Path("person_id") personId: Int): Response<NetworkPersonImages>
}
