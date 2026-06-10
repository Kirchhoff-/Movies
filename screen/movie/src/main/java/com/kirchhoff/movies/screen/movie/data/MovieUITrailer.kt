package com.kirchhoff.movies.screen.movie.data

internal data class MovieUITrailer(
    val site: String,
    val key: String
) {
    companion object {
        val Default = MovieUITrailer(
            site = "",
            key = ""
        )
    }
}
