package com.kirchhoff.movies.data.ui.details.movie

import com.kirchhoff.movies.data.ui.core.UIGenre
import com.kirchhoff.movies.ui.screens.core.credits.CreditsView

class UIMovieDetails(
    val productionCountries: List<UICountry>,
    val runtime: Int?,
    val tagLine: String?,
    val overview: String,
    val releaseDate: String?,
    val voteCount: Int?,
    val voteAverage: Float?,
    val genres: List<UIGenre>
)

data class UIMovieCastCredit(
    val name: String,
    val profilePath: String?,
    val character: String?
) : CreditsView.CreditsInfo {
    override fun title() = name
    override fun description() = character
    override fun imagePath() = profilePath
}

data class UIMovieCrewCredit(
    val name: String,
    val profilePath: String?,
    val job: String?
) : CreditsView.CreditsInfo {
    override fun title() = name
    override fun description() = job
    override fun imagePath() = profilePath
}

data class UIMovieCredits(
    val cast: List<UIMovieCastCredit>?,
    val crew: List<UIMovieCrewCredit>?
)

data class UITrailer(
    val site: String,
    val key: String
)

data class UITrailersList(val results: List<UITrailer>)

data class UICountry(val name: String)
