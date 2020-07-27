package com.kirchhoff.movies.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val backdrop_path: String?
) : Parcelable

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

@Parcelize
data class Review(
    val id: String,
    val author: String,
    val content: String
) : Parcelable

data class Trailer(
    val site: String,
    val key: String
)
