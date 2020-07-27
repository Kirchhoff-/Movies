package com.kirchhoff.movies.data.responses

import com.kirchhoff.movies.data.CountyItem
import com.kirchhoff.movies.data.Trailer

class MovieDetails(
    val production_countries: List<CountyItem>,
    val runtime: Int?,
    val tagline: String?,
    val overview: String,
    val release_date: String?
)

class TvDetails(
    val number_of_seasons: Int,
    val number_of_episodes: Int,
    val overview: String,
    val status: String,
    val first_air_date: String
)

data class PersonDetails(
    val birthday: String?,
    val place_of_birth: String?,
    val biography: String
)

data class TrailersListResponse(val results: List<Trailer>)
