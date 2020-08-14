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

data class NetworkMovieCastCredit(
    val name: String,
    val profile_path: String?,
    val character: String?
)

data class NetworkMovieCrewCredit(
    val name: String,
    val profile_path: String?,
    val job: String?
)

data class NetworkMovieCredits(
    val cast: List<NetworkMovieCastCredit>?,
    val crew: List<NetworkMovieCrewCredit>?
)

data class NetworkTrailer(
    val site: String,
    val key: String
)

data class NetworkTrailersList(val results: List<NetworkTrailer>)
