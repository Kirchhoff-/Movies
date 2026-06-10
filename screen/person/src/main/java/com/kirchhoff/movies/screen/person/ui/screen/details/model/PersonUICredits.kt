package com.kirchhoff.movies.screen.person.ui.screen.details.model

internal data class PersonUICredits(
    val cast: List<PersonUICredit.Actor>?,
    val crew: List<PersonUICredit.Creator>?
) {
    companion object {
        val Default = PersonUICredits(
            cast = emptyList(),
            crew = emptyList()
        )
    }
}
