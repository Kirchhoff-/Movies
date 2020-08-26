package com.kirchhoff.movies.data.network.core

data class NetworkEntertainmentCredits(
    val cast: List<NetworkEntertainmentPerson.Actor>?,
    val crew: List<NetworkEntertainmentPerson.Creator>?
)

sealed class NetworkEntertainmentPerson(val id: Int, val name: String, val profile_path: String?) {
    class Actor(id: Int, name: String, profile_path: String?, val character: String?) :
        NetworkEntertainmentPerson(id, name, profile_path)

    class Creator(id: Int, name: String, profile_path: String?, val job: String?) :
        NetworkEntertainmentPerson(id, name, profile_path)
}
