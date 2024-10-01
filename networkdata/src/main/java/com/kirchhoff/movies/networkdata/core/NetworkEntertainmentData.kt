package com.kirchhoff.movies.networkdata.core

import com.google.gson.annotations.SerializedName

data class NetworkEntertainmentCredits(
    @SerializedName("cast") val cast: List<NetworkEntertainmentPerson.Actor>?,
    @SerializedName("crew") val crew: List<NetworkEntertainmentPerson.Creator>?
)

sealed class NetworkEntertainmentPerson(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_path") val profilePath: String?,
    @SerializedName("popularity") val popularity: Float
) {
    class Actor(
        id: Int,
        name: String,
        profilePath: String?,
        popularity: Float,
        @SerializedName("character") val character: String?
    ) : NetworkEntertainmentPerson(id, name, profilePath, popularity)

    class Creator(
        id: Int,
        name: String,
        profilePath: String?,
        popularity: Float,
        @SerializedName("job") val job: String?
    ) : NetworkEntertainmentPerson(id, name, profilePath, popularity)
}
