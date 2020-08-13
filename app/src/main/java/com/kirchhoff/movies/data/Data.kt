package com.kirchhoff.movies.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(
    val id: Int,
    val name: String,
    val profile_path: String?
) : Parcelable

@Parcelize
data class Tv(
    val id: Int,
    val poster_path: String?,
    val backdrop_path: String?,
    val name: String
) : Parcelable
