package com.kirchhoff.movies.data.ui.core

import com.kirchhoff.movies.creditsview.CreditsView

data class UIEntertainmentCredits(
    val cast: List<UIEntertainmentPerson.Actor>?,
    val crew: List<UIEntertainmentPerson.Creator>?
)

sealed class UIEntertainmentPerson(
    open val id: Int,
    open val name: String,
    open val profilePath: String?
) {
    data class Actor(
        override val id: Int,
        override val name: String,
        override val profilePath: String?,
        private val character: String?
    ) : UIEntertainmentPerson(id, name, profilePath), CreditsView.CreditsInfo {
        override fun title() = name
        override fun description() = character
        override fun imagePath() = profilePath
    }

    data class Creator(
        override val id: Int,
        override val name: String,
        override val profilePath: String?,
        private val job: String?
    ) : UIEntertainmentPerson(id, name, profilePath), CreditsView.CreditsInfo {
        override fun title() = name
        override fun description() = job
        override fun imagePath() = profilePath
    }
}
