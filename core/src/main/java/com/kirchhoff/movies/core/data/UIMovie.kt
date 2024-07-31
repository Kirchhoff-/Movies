package com.kirchhoff.movies.core.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIMovie(
    val id: MovieId,
    val title: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Float?
) : Parcelable {
    companion object {
        val Default = UIMovie(
            id = MovieId(0),
            title = "",
            posterPath = "",
            backdropPath = "",
            voteAverage = 0.0f
        )
    }
}
