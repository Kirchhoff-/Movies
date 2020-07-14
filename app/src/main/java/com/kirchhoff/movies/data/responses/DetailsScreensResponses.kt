package com.kirchhoff.movies.data.responses

import com.kirchhoff.movies.data.CountyItem
import com.kirchhoff.movies.data.GenresItem

class MovieDetails(
    val genres: List<GenresItem>,
    val production_countries: List<CountyItem>,
    val runtime: Int?,
    val tagline: String?,
    val overview: String,
    val release_date: String?,
    val vote_count: Int,
    val vote_average: Float
)