package com.kirchhoff.movies.screen.person.ui.screen.details.model

import com.kirchhoff.movies.screen.person.data.UIPersonCredits
import com.kirchhoff.movies.screen.person.data.UIPersonDetails

internal data class PersonDetailsScreenState(
    val name: String,
    val title: String,
    val details: UIPersonDetails,
    val credits: UIPersonCredits
)
