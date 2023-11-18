package com.kirchhoff.movies.screen.credits.ui.screen.crew.model

import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.utils.StringValue

internal data class CreditsCrewScreenState(
    val creators: List<UIEntertainmentPerson.Creator>,
    val title: StringValue
)
