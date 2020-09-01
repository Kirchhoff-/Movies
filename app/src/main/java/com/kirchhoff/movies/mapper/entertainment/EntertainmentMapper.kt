package com.kirchhoff.movies.mapper.entertainment

import com.kirchhoff.movies.data.network.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.data.network.core.NetworkEntertainmentPerson
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.core.UIEntertainmentPerson
import com.kirchhoff.movies.mapper.BaseMapper

class EntertainmentMapper : BaseMapper(), IEntertainmentMapper {

    override fun createUIEntertainmentCredits(credits: NetworkEntertainmentCredits) =
        UIEntertainmentCredits(
            credits.cast?.map { createUIEntertainmentActor(it) },
            credits.crew?.map { createUIEntertainmentCreator(it) }
        )

    private fun createUIEntertainmentActor(actor: NetworkEntertainmentPerson.Actor) =
        UIEntertainmentPerson.Actor(
            actor.id,
            actor.name,
            actor.profile_path,
            actor.character
        )

    private fun createUIEntertainmentCreator(creator: NetworkEntertainmentPerson.Creator) =
        UIEntertainmentPerson.Creator(
            creator.id,
            creator.name,
            creator.profile_path,
            creator.job
        )
}
