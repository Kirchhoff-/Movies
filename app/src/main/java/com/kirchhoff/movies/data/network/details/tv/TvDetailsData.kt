package com.kirchhoff.movies.data.network.details.tv

import com.kirchhoff.movies.data.GenresItem

class NetworkTvDetails(
    val number_of_seasons: Int,
    val number_of_episodes: Int,
    val overview: String,
    val status: String,
    val first_air_date: String,
    val vote_count: Int?,
    val vote_average: Float?,
    val genres: List<GenresItem>
)

data class NetworkTvCredits(
    val cast: List<NetworkTvCastCredit>?,
    val crew: List<NetworkTvCrewCredit>?
)

data class NetworkTvCastCredit(
    val name: String,
    val profile_path: String?,
    val character: String
)

data class NetworkTvCrewCredit(
    val name: String?,
    val job: String,
    val poster_path: String?
)