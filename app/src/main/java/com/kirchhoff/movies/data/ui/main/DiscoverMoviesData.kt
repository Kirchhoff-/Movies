package com.kirchhoff.movies.data.ui.main

import android.os.Parcelable
import com.kirchhoff.movies.data.ui.core.Paginated
import kotlinx.android.parcel.Parcelize

data class UIDiscoverMovies(
    override val page: Int,
    override val results: List<UIMovie>,
    override val totalPages: Int
) : Paginated<UIMovie>

@Parcelize
data class UIMovie(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val backdropPath: String?
) : Parcelable
