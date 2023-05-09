package com.kirchhoff.movies.creditsview.data

import com.kirchhoff.movies.core.data.UIEntertainmentPerson

data class CreditsInfo(
    val id: Int,
    val title: String?,
    val description: String?,
    val imagePath: String?
) {
    constructor(actor: UIEntertainmentPerson.Actor) : this(
        actor.id,
        actor.name,
        actor.character,
        actor.profilePath
    )

    constructor(creator: UIEntertainmentPerson.Creator) : this(
        creator.id,
        creator.name,
        creator.job,
        creator.profilePath
    )
}
