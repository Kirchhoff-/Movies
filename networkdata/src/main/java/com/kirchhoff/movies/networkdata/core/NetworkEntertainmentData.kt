package com.kirchhoff.movies.networkdata.core

import com.google.gson.annotations.SerializedName

data class NetworkEntertainmentCredits(
    val cast: List<NetworkEntertainmentPerson.Actor>?,
    val crew: List<NetworkEntertainmentPerson.Creator>?
)

sealed class NetworkEntertainmentPerson(
    val id: Int,
    val name: String,
    @SerializedName("profile_path") val profilePath: String?,
    @SerializedName("popularity") val popularity: Float
) {
    class Actor(
        id: Int,
        name: String,
        profilePath: String?,
        popularity: Float,
        val character: String?
    ) : NetworkEntertainmentPerson(id, name, profilePath, popularity)

    class Creator(
        id: Int,
        name: String,
        profilePath: String?,
        popularity: Float,
        val job: String?
    ) : NetworkEntertainmentPerson(id, name, profilePath, popularity)
}
