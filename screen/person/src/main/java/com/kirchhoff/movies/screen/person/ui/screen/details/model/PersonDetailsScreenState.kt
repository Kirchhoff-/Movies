package com.kirchhoff.movies.screen.person.ui.screen.details.model

import com.kirchhoff.movies.screen.person.data.PersonUIImage

internal data class PersonDetailsScreenState(
    val name: String,
    val title: String,
    val details: PersonUIDetails,
    val credits: PersonUICredits,
    val images: List<PersonUIImage>,
    val isLoading: Boolean,
    val errorMessage: String
) {
    companion object {
        val Default = PersonDetailsScreenState(
            name = "",
            title = "",
            details = PersonUIDetails.Default,
            credits = PersonUICredits.Default,
            images = emptyList(),
            isLoading = false,
            errorMessage = ""
        )
    }
}
