package com.kirchhoff.movies.mapper.core

import com.kirchhoff.movies.data.network.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.data.network.core.NetworkEntertainmentPerson
import com.kirchhoff.movies.data.network.core.NetworkObjectWithName
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.core.UIEntertainmentPerson
import com.kirchhoff.movies.data.ui.core.UIGenre
import com.kirchhoff.movies.mapper.BaseMapper

class CoreMapper : BaseMapper(), ICoreMapper {

    override fun createUIEntertainmentCredits(credits: NetworkEntertainmentCredits) =
        UIEntertainmentCredits(
            credits.cast?.map { createUIEntertainmentActor(it) },
            credits.crew?.map { createUIEntertainmentCreator(it) }
        )

    override fun createUIGenre(item: NetworkObjectWithName) = UIGenre(item.name)

    private fun createUIEntertainmentActor(actor: NetworkEntertainmentPerson.Actor) =
        UIEntertainmentPerson.Actor(
            actor.id,
            actor.name,
            actor.profilePath,
            actor.character
        )

    private fun createUIEntertainmentCreator(creator: NetworkEntertainmentPerson.Creator) =
        UIEntertainmentPerson.Creator(
            creator.id,
            creator.name,
            creator.profilePath,
            creator.job
        )
}
