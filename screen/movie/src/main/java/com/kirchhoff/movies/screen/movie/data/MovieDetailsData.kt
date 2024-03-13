package com.kirchhoff.movies.screen.movie.data

import android.os.Parcelable
import com.kirchhoff.movies.core.data.UIGenre
import kotlinx.parcelize.Parcelize

internal data class UIMovieInfo(
    val productionCountries: List<UICountry>,
    val productionCompanies: List<UIProductionCompany>,
    val runtime: Int?,
    val tagLine: String?,
    val overview: String,
    val releaseDate: String?,
    val voteCount: Int?,
    val voteAverage: Float?,
    val genres: List<UIGenre>
)

internal data class UITrailer(
    val site: String,
    val key: String
)

internal data class UICountry(val id: String, val name: String)

@Parcelize
internal data class UIProductionCompany(
    val id: String,
    val logoPath: String,
    val name: String
) : Parcelable
