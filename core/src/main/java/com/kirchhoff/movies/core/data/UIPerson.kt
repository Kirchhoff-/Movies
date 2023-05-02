package com.kirchhoff.movies.core.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIPerson(
    val id: Int,
    val name: String,
    val profilePath: String?
) : Parcelable
