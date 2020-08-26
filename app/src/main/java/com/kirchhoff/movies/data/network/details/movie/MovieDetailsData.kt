package com.kirchhoff.movies.data.network.details.movie

import com.kirchhoff.movies.data.network.core.NetworkObjectWithName

class NetworkMovieDetails(
    val production_countries: List<NetworkObjectWithName>,
    val runtime: Int?,
    val tagline: String?,
    val overview: String,
    val release_date: String?,
    val vote_count: Int?,
    val vote_average: Float?,
    val genres: List<NetworkObjectWithName>
)

data class NetworkTrailer(
    val site: String,
    val key: String
)

data class NetworkTrailersList(val results: List<NetworkTrailer>)
