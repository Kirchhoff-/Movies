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
) {
    companion object {
        val Default = UIMovieInfo(
            productionCountries = emptyList(),
            productionCompanies = emptyList(),
            runtime = 0,
            tagLine = "",
            overview = "",
            releaseDate = "",
            voteCount = 0,
            voteAverage = 0f,
            genres = emptyList()
        )
    }
}

internal data class UITrailer(
    val site: String,
    val key: String
) {
    companion object {
        val Default = UITrailer(
            site = "",
            key = ""
        )
    }
}

@Parcelize
internal data class UICountry(val id: String, val name: String) : Parcelable

@Parcelize
internal data class UIProductionCompany(
    val id: String,
    val logoPath: String,
    val name: String
) : Parcelable {
    companion object {
        val Default = UIProductionCompany(
            id = "",
            logoPath = "",
            name = ""
        )
    }
}
