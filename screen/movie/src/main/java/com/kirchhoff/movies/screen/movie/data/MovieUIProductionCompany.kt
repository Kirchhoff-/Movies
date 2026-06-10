package com.kirchhoff.movies.screen.movie.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class MovieUIProductionCompany(
    val id: String,
    val logoPath: String,
    val name: String
) : Parcelable {
    companion object {
        val Default = MovieUIProductionCompany(
            id = "",
            logoPath = "",
            name = ""
        )
    }
}
