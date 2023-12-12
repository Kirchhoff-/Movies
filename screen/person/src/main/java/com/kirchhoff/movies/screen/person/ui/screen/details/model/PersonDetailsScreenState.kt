package com.kirchhoff.movies.screen.person.ui.screen.details.model

import com.kirchhoff.movies.screen.person.data.UIPersonCredits
import com.kirchhoff.movies.screen.person.data.UIPersonDetails
import com.kirchhoff.movies.screen.person.data.UIPersonImage

internal data class PersonDetailsScreenState(
    val name: String,
    val title: String,
    val details: UIPersonDetails,
    val credits: UIPersonCredits,
    val images: List<UIPersonImage>,
    val isLoading: Boolean,
    val errorMessage: String
)
