package com.kirchhoff.movies.core.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class UIEntertainmentCredits(
    val cast: List<UIEntertainmentPerson.Actor>?,
    val crew: List<UIEntertainmentPerson.Creator>?
) {
    fun findPerson(id: Int): UIEntertainmentPerson? =
        cast?.find { it.id == id } ?: crew?.find { it.id == id }
}

sealed class UIEntertainmentPerson(
    open val id: Int,
    open val name: String,
    open val profilePath: String?
) {
    @Parcelize
    data class Actor(
        override val id: Int,
        override val name: String,
        override val profilePath: String?,
        val character: String?
    ) : UIEntertainmentPerson(id, name, profilePath), Parcelable

    data class Creator(
        override val id: Int,
        override val name: String,
        override val profilePath: String?,
        val job: String?
    ) : UIEntertainmentPerson(id, name, profilePath)
}
