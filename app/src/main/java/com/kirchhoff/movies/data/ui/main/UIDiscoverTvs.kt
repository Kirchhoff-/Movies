package com.kirchhoff.movies.data.ui.main

import android.os.Parcelable
import com.kirchhoff.movies.data.ui.core.Paginated
import kotlinx.android.parcel.Parcelize

data class UIDiscoverTvs(
    override val page: Int,
    override val results: List<UITv>,
    override val totalPages: Int
) : Paginated<UITv>

@Parcelize
data class UITv(
    val id: Int,
    val posterPath: String?,
    val backdropPath: String?,
    val name: String
) : Parcelable
