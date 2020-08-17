package com.kirchhoff.movies.data.ui.main

import android.os.Parcelable
import com.kirchhoff.movies.data.ui.core.Paginated
import kotlinx.android.parcel.Parcelize

class UIPersons(
    override val page: Int,
    override val results: List<UIPerson>,
    override val totalPages: Int
) : Paginated<UIPerson>

@Parcelize
data class UIPerson(
    val id: Int,
    val name: String,
    val profilePath: String?
) : Parcelable
