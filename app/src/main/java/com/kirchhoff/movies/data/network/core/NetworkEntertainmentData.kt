package com.kirchhoff.movies.data.network.core

import com.google.gson.annotations.SerializedName

data class NetworkEntertainmentCredits(
    val cast: List<NetworkEntertainmentPerson.Actor>?,
    val crew: List<NetworkEntertainmentPerson.Creator>?
)

sealed class NetworkEntertainmentPerson(
    val id: Int,
    val name: String,
    @SerializedName("profile_path") val profilePath: String?
) {
    class Actor(id: Int, name: String, profilePath: String?, val character: String?) :
        NetworkEntertainmentPerson(id, name, profilePath)

    class Creator(id: Int, name: String, profilePath: String?, val job: String?) :
        NetworkEntertainmentPerson(id, name, profilePath)
}
