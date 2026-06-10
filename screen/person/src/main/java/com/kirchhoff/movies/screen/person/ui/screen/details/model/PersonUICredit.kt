package com.kirchhoff.movies.screen.person.ui.screen.details.model

internal sealed class PersonUICredit(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val backdropPath: String?,
    val mediaType: PersonUIMediaType
) {
    class Actor(
        id: Int,
        title: String,
        posterPath: String?,
        backdropPath: String?,
        mediaType: PersonUIMediaType,
        val character: String?
    ) : PersonUICredit(id, title, posterPath, backdropPath, mediaType) {
        companion object {
            val Default = Actor(
                id = 0,
                title = "",
                posterPath = "",
                backdropPath = "",
                mediaType = PersonUIMediaType.MOVIE,
                character = ""
            )
        }
    }

    class Creator(
        id: Int,
        title: String,
        posterPath: String?,
        backdropPath: String?,
        mediaType: PersonUIMediaType,
        val job: String
    ) : PersonUICredit(id, title, posterPath, backdropPath, mediaType)
}
