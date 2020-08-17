package com.kirchhoff.movies.data.ui.details.person

import com.kirchhoff.movies.ui.screens.core.credits.CreditsView

data class UIPersonDetails(
    val birthday: String?,
    val placeOfBirth: String?,
    val biography: String,
    val alsoKnownAs: List<String>?
)

data class UIPersonCredits(
    val cast: List<UIPersonCastCredit>?,
    val crew: List<UIPersonCrewCredit>?
)

data class UIPersonCastCredit(
    val title: String?,
    val character: String?,
    val posterPath: String?
) : CreditsView.CreditsInfo {
    override fun title() = title
    override fun description() = character
    override fun imagePath() = posterPath
}

data class UIPersonCrewCredit(
    val title: String?,
    val job: String,
    val posterPath: String?
) : CreditsView.CreditsInfo {
    override fun title() = title
    override fun description() = job
    override fun imagePath() = posterPath
}
