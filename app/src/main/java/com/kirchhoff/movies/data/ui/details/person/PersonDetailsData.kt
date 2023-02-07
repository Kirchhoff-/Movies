package com.kirchhoff.movies.data.ui.details.person

import com.kirchhoff.movies.creditsview.CreditsView

data class UIPersonDetails(
    val birthday: String?,
    val placeOfBirth: String?,
    val biography: String,
    val alsoKnownAs: List<String>?
)

data class UIPersonCredits(
    val cast: List<UIPersonCredit.Actor>?,
    val crew: List<UIPersonCredit.Creator>?
)

sealed class UIPersonCredit(
    val id: Int,
    val title: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val mediaType: UIMediaType
) {
    class Actor(
        id: Int,
        title: String?,
        posterPath: String?,
        backdropPath: String?,
        mediaType: UIMediaType,
        val character: String?
    ) : UIPersonCredit(id, title, posterPath, backdropPath, mediaType), CreditsView.CreditsInfo {
        override fun title() = title
        override fun description() = character
        override fun imagePath() = posterPath
    }

    class Creator(
        id: Int,
        title: String?,
        posterPath: String?,
        backdropPath: String?,
        mediaType: UIMediaType,
        val job: String
    ) : UIPersonCredit(id, title, posterPath, backdropPath, mediaType), CreditsView.CreditsInfo {
        override fun title() = title
        override fun description() = job
        override fun imagePath() = posterPath
    }
}

enum class UIMediaType { MOVIE, TV }
