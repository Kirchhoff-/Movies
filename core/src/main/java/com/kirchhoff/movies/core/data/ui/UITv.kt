package com.kirchhoff.movies.core.data.ui

import android.os.Parcelable
import com.kirchhoff.movies.core.data.TvId
import kotlinx.parcelize.Parcelize

@Parcelize
data class UITv(
    val id: TvId,
    val name: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Float?
) : Parcelable {
    companion object {
        val Default = UITv(
            id = TvId(0),
            name = "",
            posterPath = "",
            backdropPath = "",
            voteAverage = 0.0f
        )
    }
}
