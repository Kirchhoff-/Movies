package com.kirchhoff.movies.screen.tvshow.ui.screen.details.model

internal data class TvShowDetailsInfo(
    val posterPath: String?,
    val numberOfSeasons: Int,
    val numberOfEpisodes: Int,
    val status: String,
    val firstAirDate: String,
    val voteCount: Int?,
    val voteAverage: Float?
) {
    companion object {
        val Default = TvShowDetailsInfo(
            posterPath = null,
            numberOfSeasons = 0,
            numberOfEpisodes = 0,
            status = "",
            firstAirDate = "",
            voteCount = 0,
            voteAverage = 0f
        )
    }
}
