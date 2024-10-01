package com.kirchhoff.movies.networkdata.details.person

import com.google.gson.annotations.SerializedName

data class NetworkPersonDetails(
    @SerializedName("birthday") val birthday: String?,
    @SerializedName("place_of_birth") val placeOfBirth: String?,
    @SerializedName("biography") val biography: String,
    @SerializedName("also_known_as") val alsoKnownAs: List<String>?
)

data class NetworkPersonCredits(
    @SerializedName("cast") val cast: List<NetworkPersonCastCredit>?,
    @SerializedName("crew") val crew: List<NetworkPersonCrewCredit>?
)

data class NetworkPersonCastCredit(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("character") val character: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_patch") val backdropPath: String?,
    @SerializedName("media_type") val mediaType: String
)

data class NetworkPersonCrewCredit(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("job") val job: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("media_type") val mediaType: String
)
