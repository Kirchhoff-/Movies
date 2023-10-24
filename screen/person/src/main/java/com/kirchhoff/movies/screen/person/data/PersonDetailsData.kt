package com.kirchhoff.movies.screen.person.data

internal data class UIPersonDetails(
    val birthday: String?,
    val placeOfBirth: String?,
    val biography: String,
    val alsoKnownAs: List<String>?
)

internal data class UIPersonCredits(
    val cast: List<UIPersonCredit.Actor>?,
    val crew: List<UIPersonCredit.Creator>?
) {
    fun findCredit(id: Int): UIPersonCredit? =
        cast?.find { it.id == id } ?: crew?.find { it.id == id }
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
    ) : UIPersonCredit(id, title, posterPath, backdropPath, mediaType)

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
