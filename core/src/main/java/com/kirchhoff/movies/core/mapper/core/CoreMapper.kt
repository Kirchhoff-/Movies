package com.kirchhoff.movies.core.mapper.core

import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.data.UIGenre
import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentPerson
import com.kirchhoff.movies.networkdata.core.NetworkObjectWithName

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
