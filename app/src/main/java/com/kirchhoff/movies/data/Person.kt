package com.kirchhoff.movies.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(
    val id: Int,
    val name: String,
    val profile_path: String?
) : Parcelable
