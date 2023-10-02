package com.kirchhoff.movies.creditsview.data

import androidx.annotation.DrawableRes
import com.kirchhoff.movies.core.data.UIEntertainmentPerson

data class CreditsInfo(
    val id: Int,
    val title: String?,
    val description: String?,
    val imagePath: String?,
    @DrawableRes val placeholderImageResources: Int
) {
    constructor(actor: UIEntertainmentPerson.Actor) : this(
        actor.id,
        actor.name,
        actor.character,
        actor.profilePath,
        com.kirchhoff.movies.core.R.drawable.ic_empty_avatar
    )

    constructor(creator: UIEntertainmentPerson.Creator) : this(
        creator.id,
        creator.name,
        creator.job,
        creator.profilePath,
        com.kirchhoff.movies.core.R.drawable.ic_empty_avatar
    )
}
