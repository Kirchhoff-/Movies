package com.kirchhoff.movies.screen.person.ui.screen.details.model

import com.kirchhoff.movies.screen.person.data.UIPersonImage

internal data class PersonDetailsScreenState(
    val name: String,
    val title: String,
    val details: UIPersonDetails,
    val credits: UIPersonCredits,
    val images: List<UIPersonImage>,
    val isLoading: Boolean,
    val errorMessage: String
) {
    companion object {
        val Default = PersonDetailsScreenState(
            name = "",
            title = "",
            details = UIPersonDetails.Default,
            credits = UIPersonCredits.Default,
            images = emptyList(),
            isLoading = false,
            errorMessage = ""
        )
    }
}
