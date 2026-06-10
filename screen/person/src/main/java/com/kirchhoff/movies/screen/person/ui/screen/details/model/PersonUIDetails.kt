package com.kirchhoff.movies.screen.person.ui.screen.details.model

internal data class PersonUIDetails(
    val birthday: String?,
    val placeOfBirth: String?,
    val biography: String,
    val alsoKnownAs: List<String>?,
    val homepage: String?
) {
    companion object {
        val Default = PersonUIDetails(
            birthday = "",
            placeOfBirth = "",
            biography = "",
            alsoKnownAs = emptyList(),
            homepage = null
        )
    }
}
