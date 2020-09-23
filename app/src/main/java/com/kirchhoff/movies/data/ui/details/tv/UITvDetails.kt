package com.kirchhoff.movies.data.ui.details.tv

import com.kirchhoff.movies.data.ui.core.UIGenre

data class UITvDetails(
    val numberOfSeasons: Int,
    val numberOfEpisodes: Int,
    val overview: String,
    val status: String,
    val firstAirDate: String,
    val voteCount: Int?,
    val voteAverage: Float?,
    val genres: List<UIGenre>
)
