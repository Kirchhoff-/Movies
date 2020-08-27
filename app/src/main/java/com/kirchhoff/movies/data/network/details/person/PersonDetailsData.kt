package com.kirchhoff.movies.data.network.details.person

data class NetworkPersonDetails(
    val birthday: String?,
    val place_of_birth: String?,
    val biography: String,
    val also_known_as: List<String>?
)

data class NetworkPersonCredits(
    val cast: List<NetworkPersonCastCredit>?,
    val crew: List<NetworkPersonCrewCredit>?
)

data class NetworkPersonCastCredit(
    val id: Int,
    val title: String?,
    val character: String?,
    val poster_path: String?,
    val backdrop_path: String?,
    val media_type: String
)

data class NetworkPersonCrewCredit(
    val id: Int,
    val title: String?,
    val job: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val media_type: String
)
