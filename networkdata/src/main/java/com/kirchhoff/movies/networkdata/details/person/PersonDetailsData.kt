package com.kirchhoff.movies.networkdata.details.person

import com.google.gson.annotations.SerializedName

data class NetworkPersonCredits(
    val cast: List<NetworkPersonCastCredit>?,
    val crew: List<NetworkPersonCrewCredit>?
)

data class NetworkPersonCastCredit(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("character") val character: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_patch") val backdropPath: String?,
    @SerializedName("media_type") val mediaType: String
)

data class NetworkPersonCrewCredit(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("job") val job: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("media_type") val mediaType: String
)
