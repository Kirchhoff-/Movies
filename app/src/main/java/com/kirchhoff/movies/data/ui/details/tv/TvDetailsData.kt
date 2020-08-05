package com.kirchhoff.movies.data.ui.details.tv

import com.kirchhoff.movies.data.GenresItem
import com.kirchhoff.movies.data.responses.CreditsInfo

class UITvDetails(
    val numberOfSeasons: Int,
    val numberOfEpisodes: Int,
    val overview: String,
    val status: String,
    val firstAirDate: String,
    val voteCount: Int?,
    val voteAverage: Float?,
    val genres: List<GenresItem>
)

data class UITvCredits(
    val cast: List<UITvCastCredit>?,
    val crew: List<UITvCrewCredit>?
)

data class UITvCastCredit(
    val name: String,
    val character: String,
    val profilePath: String?
) : CreditsInfo {
    override fun title() = name
    override fun description() = character
    override fun imagePath() = profilePath
}

data class UITvCrewCredit(
    val name: String?,
    val job: String,
    val posterPath: String?
) : CreditsInfo {
    override fun title() = name
    override fun description() = job
    override fun imagePath() = posterPath
}
