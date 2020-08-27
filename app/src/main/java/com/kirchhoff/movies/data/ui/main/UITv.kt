package com.kirchhoff.movies.data.ui.main

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UITv(
    val id: Int,
    val name: String?,
    val posterPath: String?,
    val backdropPath: String?
) : Parcelable
