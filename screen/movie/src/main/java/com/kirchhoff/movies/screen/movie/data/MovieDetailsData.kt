package com.kirchhoff.movies.screen.movie.data

import com.kirchhoff.movies.core.data.UIGenre

data class UIMovieDetails(
    val productionCountries: List<UICountry>,
    val runtime: Int?,
    val tagLine: String?,
    val overview: String,
    val releaseDate: String?,
    val voteCount: Int?,
    val voteAverage: Float?,
    val genres: List<UIGenre>
)

data class UITrailer(
    val site: String,
    val key: String
)

data class UITrailersList(val results: List<UITrailer>)

data class UICountry(val id: String, val name: String)
