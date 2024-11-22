package com.kirchhoff.movies.screen.person.ui.screen.details.model

internal data class UIPersonDetails(
    val birthday: String?,
    val placeOfBirth: String?,
    val biography: String,
    val alsoKnownAs: List<String>?
) {
    companion object {
        val Default = UIPersonDetails(
            birthday = "",
            placeOfBirth = "",
            biography = "",
            alsoKnownAs = emptyList()
        )
    }
}

internal data class UIPersonCredits(
    val cast: List<UIPersonCredit.Actor>?,
    val crew: List<UIPersonCredit.Creator>?
) {
    companion object {
        val Default = UIPersonCredits(
            cast = emptyList(),
            crew = emptyList()
        )
    }
}

internal sealed class UIPersonCredit(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val backdropPath: String?,
    val mediaType: UIMediaType
) {
    class Actor(
        id: Int,
        title: String,
        posterPath: String?,
        backdropPath: String?,
        mediaType: UIMediaType,
        val character: String?
    ) : UIPersonCredit(id, title, posterPath, backdropPath, mediaType) {
        companion object {
            val Default = Actor(
                id = 0,
                title = "",
                posterPath = "",
                backdropPath = "",
                mediaType = UIMediaType.MOVIE,
                character = ""
            )
        }
    }

    class Creator(
        id: Int,
        title: String,
        posterPath: String?,
        backdropPath: String?,
        mediaType: UIMediaType,
        val job: String
    ) : UIPersonCredit(id, title, posterPath, backdropPath, mediaType)
}

internal enum class UIMediaType { MOVIE, TV }
