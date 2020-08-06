package com.kirchhoff.movies.data.ui.details.movie

import com.kirchhoff.movies.data.CountyItem
import com.kirchhoff.movies.data.GenresItem
import com.kirchhoff.movies.data.responses.CreditsInfo

class UIMovieDetails(
    val productionCountries: List<CountyItem>,
    val runtime: Int?,
    val tagLine: String?,
    val overview: String,
    val releaseDate: String?,
    val voteCount: Int?,
    val voteAverage: Float?,
    val genres: List<GenresItem>
)

data class UIMovieCastCredit(
    val name: String,
    val profilePath: String?,
    val character: String?
) : CreditsInfo {
    override fun title() = name
    override fun description() = character
    override fun imagePath() = profilePath
}

data class UIMovieCrewCredit(
    val name: String,
    val profilePath: String?,
    val job: String?
) : CreditsInfo {
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
