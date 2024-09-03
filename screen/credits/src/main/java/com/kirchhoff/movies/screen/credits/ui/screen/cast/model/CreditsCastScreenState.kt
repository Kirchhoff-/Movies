package com.kirchhoff.movies.screen.credits.ui.screen.cast.model

import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.utils.StringValue

internal data class CreditsCastScreenState(
    val actors: List<UIEntertainmentPerson.Actor>,
    val title: StringValue
) {
    companion object {
        val Default = CreditsCastScreenState(
            actors = emptyList(),
            title = StringValue.Empty
        )
    }
}
