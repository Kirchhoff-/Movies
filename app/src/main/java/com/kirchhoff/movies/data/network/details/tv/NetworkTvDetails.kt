package com.kirchhoff.movies.data.network.details.tv

import com.kirchhoff.movies.data.network.core.NetworkObjectWithName

class NetworkTvDetails(
    val number_of_seasons: Int,
    val number_of_episodes: Int,
    val overview: String,
    val status: String,
    val first_air_date: String,
    val vote_count: Int?,
    val vote_average: Float?,
    val genres: List<NetworkObjectWithName>
)
