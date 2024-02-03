package com.kirchhoff.movies.screen.movie.data

import com.kirchhoff.movies.core.data.UIGenre

internal data class UIMovieInfo(
    val productionCountries: List<UICountry>,
    val runtime: Int?,
    val tagLine: String?,
    val overview: String,
    val releaseDate: String?,
    val voteCount: Int?,
    val voteAverage: Float?,
    val genres: List<UIGenre>
)

internal data class UITrailer(
    val site: String,
    val key: String
)

internal data class UITrailersList(val results: List<UITrailer>)

internal data class UICountry(val id: String, val name: String)
