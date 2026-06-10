package com.kirchhoff.movies.screen.movie.data

import com.kirchhoff.movies.core.data.ui.UIGenre

internal data class MovieUIInfo(
    val productionCountries: List<MovieUICountry>,
    val productionCompanies: List<MovieUIProductionCompany>,
    val runtime: Int?,
    val tagLine: String?,
    val overview: String,
    val releaseDate: String?,
    val voteCount: Int?,
    val voteAverage: Float?,
    val genres: List<UIGenre>
) {
    companion object {
        val Default = MovieUIInfo(
            productionCountries = emptyList(),
            productionCompanies = emptyList(),
            runtime = 0,
            tagLine = "",
            overview = "",
            releaseDate = "",
            voteCount = 0,
            voteAverage = 0f,
            genres = emptyList()
        )
    }
}
