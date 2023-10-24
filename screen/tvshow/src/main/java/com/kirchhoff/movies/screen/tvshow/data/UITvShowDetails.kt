package com.kirchhoff.movies.screen.tvshow.data

import com.kirchhoff.movies.core.data.UIGenre

internal data class UITvShowDetails(
    val numberOfSeasons: Int,
    val numberOfEpisodes: Int,
    val overview: String,
    val status: String,
    val firstAirDate: String,
    val voteCount: Int?,
    val voteAverage: Float?,
    val genres: List<UIGenre>
)
