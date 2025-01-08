package com.kirchhoff.movies.core.data.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIGenre(
    val id: String,
    val name: String
) : Parcelable {
    companion object {
        val Default = UIGenre(
            id = "",
            name = ""
        )
    }
}
