package com.kirchhoff.movies.core.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UITv(
    val id: TvId,
    val name: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Float?
) : Parcelable
