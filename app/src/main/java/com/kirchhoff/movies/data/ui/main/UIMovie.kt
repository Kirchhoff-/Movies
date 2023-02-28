package com.kirchhoff.movies.data.ui.main

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UIMovie(
    val id: Int,
    val title: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Float?
) : Parcelable
