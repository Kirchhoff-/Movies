package com.kirchhoff.movies.data.ui.core

import com.kirchhoff.movies.ui.screens.core.credits.CreditsView

data class UIEntertainmentCredits(
    val cast: List<UIEntertainmentPerson.Actor>?,
    val crew: List<UIEntertainmentPerson.Creator>?
)

sealed class UIEntertainmentPerson(val id: Int, val name: String, val profilePath: String?) {
    class Actor(id: Int, name: String, profilePath: String?, val character: String?) :
        UIEntertainmentPerson(id, name, profilePath), CreditsView.CreditsInfo {
        override fun title() = name
        override fun description() = character
        override fun imagePath() = profilePath
    }

    class Creator(id: Int, name: String, profilePath: String?, val job: String?) :
        UIEntertainmentPerson(id, name, profilePath), CreditsView.CreditsInfo {
        override fun title() = name
        override fun description() = job
        override fun imagePath() = profilePath
    }
}
