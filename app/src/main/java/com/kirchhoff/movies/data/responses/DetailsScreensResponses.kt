package com.kirchhoff.movies.data.responses

import com.kirchhoff.movies.data.CountyItem

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
