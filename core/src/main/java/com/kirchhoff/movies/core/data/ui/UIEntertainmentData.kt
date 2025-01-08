package com.kirchhoff.movies.core.data.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class UIEntertainmentCredits(
    val cast: List<UIEntertainmentPerson.Actor>?,
    val crew: List<UIEntertainmentPerson.Creator>?
) {
    companion object {
        val Default: UIEntertainmentCredits = UIEntertainmentCredits(
            cast = emptyList(),
            crew = emptyList()
        )
    }
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
    ) : UIEntertainmentPerson(id, name, profilePath), Parcelable {
        companion object {
            val Default = Actor(
                id = 0,
                name = "",
                profilePath = "",
                character = ""
            )
        }
    }

    @Parcelize
    data class Creator(
        override val id: Int,
        override val name: String,
        override val profilePath: String?,
        val job: String?
    ) : UIEntertainmentPerson(id, name, profilePath), Parcelable
}
