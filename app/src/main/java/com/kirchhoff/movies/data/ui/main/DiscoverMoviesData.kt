package com.kirchhoff.movies.data.ui.main

import android.os.Parcelable
import com.kirchhoff.movies.data.responses.PaginatedResponse
import kotlinx.android.parcel.Parcelize

data class UIDiscoverMovies(
    override val page: Int,
    override val results: List<UIMovie>,
    override val total_results: Int,
    override val total_pages: Int
) : PaginatedResponse<UIMovie>

@Parcelize
data class UIMovie(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val backdropPath: String?
) : Parcelable
