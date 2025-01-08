package com.kirchhoff.movies.screen.tvshow.ui.screen.details.model

import com.kirchhoff.movies.core.data.ui.UIGenre

internal data class TvShowDetails(
    val numberOfSeasons: Int,
    val numberOfEpisodes: Int,
    val overview: String,
    val status: String,
    val firstAirDate: String,
    val voteCount: Int?,
    val voteAverage: Float?,
    val genres: List<UIGenre>
)
