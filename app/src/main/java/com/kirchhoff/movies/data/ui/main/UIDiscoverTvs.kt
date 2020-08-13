package com.kirchhoff.movies.data.ui.main

import android.os.Parcelable
import com.kirchhoff.movies.data.responses.PaginatedResponse
import kotlinx.android.parcel.Parcelize

data class UIDiscoverTvs(
    override val page: Int,
    override val results: List<UITv>,
    override val total_results: Int,
    override val total_pages: Int
) : PaginatedResponse<UITv>

@Parcelize
data class UITv(
    val id: Int,
    val posterPath: String?,
    val backdropPath: String?,
    val name: String
) : Parcelable
