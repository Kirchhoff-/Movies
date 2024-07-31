package com.kirchhoff.movies.screen.tvshow.ui.screen.details.model

import com.kirchhoff.movies.core.data.UIGenre

internal data class TvShowDetailsInfo(
    val numberOfSeasons: Int,
    val numberOfEpisodes: Int,
    val overview: String,
    val status: String,
    val firstAirDate: String,
    val voteCount: Int?,
    val voteAverage: Float?,
    val genres: List<UIGenre>
) {
    companion object {
        val Default = TvShowDetailsInfo(
            numberOfSeasons = 0,
            numberOfEpisodes = 0,
            overview = "",
            status = "",
            firstAirDate = "",
            voteAverage = 0f,
            voteCount = 0,
            genres = emptyList()
        )
    }
}
