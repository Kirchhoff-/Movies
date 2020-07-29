package com.kirchhoff.movies.data.responses

import com.kirchhoff.movies.data.CountyItem
import com.kirchhoff.movies.data.GenresItem
import com.kirchhoff.movies.data.Trailer

class MovieDetails(
    val production_countries: List<CountyItem>,
    val runtime: Int?,
    val tagline: String?,
    val overview: String,
    val release_date: String?,
    val vote_count: Int?,
    val vote_average: Float?,
    val genres: List<GenresItem>
)

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

data class PersonDetails(
    val birthday: String?,
    val place_of_birth: String?,
    val biography: String,
    val also_known_as: List<String>?
)

data class TrailersListResponse(val results: List<Trailer>)
