package com.kirchhoff.movies.data.responses

import com.kirchhoff.movies.data.CountyItem

class MovieDetails(
    val production_countries: List<CountyItem>,
    val runtime: Int?,
    val tagline: String?,
    val overview: String,
    val release_date: String?
)
