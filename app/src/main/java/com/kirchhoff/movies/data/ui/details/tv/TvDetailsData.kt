package com.kirchhoff.movies.data.ui.details.tv

import com.kirchhoff.movies.data.ui.core.UIGenre
import com.kirchhoff.movies.ui.screens.core.credits.CreditsView

class UITvDetails(
    val numberOfSeasons: Int,
    val numberOfEpisodes: Int,
    val overview: String,
    val status: String,
    val firstAirDate: String,
    val voteCount: Int?,
    val voteAverage: Float?,
    val genres: List<UIGenre>
)

data class UITvCredits(
    val cast: List<UITvCastCredit>?,
    val crew: List<UITvCrewCredit>?
)

data class UITvCastCredit(
    val id: Int,
    val name: String,
    val character: String,
    val profilePath: String?
) : CreditsView.CreditsInfo {
    override fun title() = name
    override fun description() = character
    override fun imagePath() = profilePath
}

data class UITvCrewCredit(
    val id: Int,
    val name: String,
    val job: String,
    val profilePath: String?
) : CreditsView.CreditsInfo {
    override fun title() = name
    override fun description() = job
    override fun imagePath() = profilePath
}
