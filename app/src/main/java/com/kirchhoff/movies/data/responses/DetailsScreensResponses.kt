package com.kirchhoff.movies.data.responses

import com.kirchhoff.movies.data.GenresItem
import com.kirchhoff.movies.data.Trailer

class TvDetails(
    val number_of_seasons: Int,
    val number_of_episodes: Int,
    val overview: String,
    val status: String,
    val first_air_date: String,
    val vote_count: Int?,
    val vote_average: Float?,
    val genres: List<GenresItem>
)

data class TrailersListResponse(val results: List<Trailer>)

data class TvCredit(
    val name: String,
    val profile_path: String?,
    val character: String
) : CreditsInfo {
    override fun title() = name

    override fun description() = character

    override fun imagePath() = profile_path
}

data class TvCredits(
    val cast: List<TvCredit>?,
    val crew: List<TvCredit>?
)
