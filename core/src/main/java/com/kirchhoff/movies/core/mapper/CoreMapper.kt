package com.kirchhoff.movies.core.mapper

import com.kirchhoff.movies.core.data.ui.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.ui.UIEntertainmentPerson
import com.kirchhoff.movies.core.data.ui.UIGenre
import com.kirchhoff.movies.core.data.ui.UIImage
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentPerson
import com.kirchhoff.movies.networkdata.core.NetworkObjectWithName
import com.kirchhoff.movies.networkdata.main.NetworkImage

class CoreMapper : BaseMapper() {

    fun createUIEntertainmentCredits(credits: NetworkEntertainmentCredits): UIEntertainmentCredits = credits.toUICredits()

    fun createUIGenre(item: NetworkObjectWithName): UIGenre = UIGenre(item.id, item.name)

    fun createUIImage(item: NetworkImage): UIImage = UIImage(
        path = item.path,
        height = item.height / IMAGE_SIZE_KF,
        width = item.width / IMAGE_SIZE_KF
    )

    fun createUIEntertainmentActors(actors: List<NetworkEntertainmentPerson.Actor>): List<UIEntertainmentPerson.Actor> =
        actors.map { it.toUIActor() }

    fun createUIEntertainmentCreators(creators: List<NetworkEntertainmentPerson.Creator>): List<UIEntertainmentPerson.Creator> =
        creators.map { it.toUICreator() }

    private fun NetworkEntertainmentCredits.toUICredits(): UIEntertainmentCredits = UIEntertainmentCredits(
        cast = cast?.let { createUIEntertainmentActors(it) },
        crew = crew?.let { createUIEntertainmentCreators(it) }
    )

    private fun NetworkEntertainmentPerson.Actor.toUIActor(): UIEntertainmentPerson.Actor = UIEntertainmentPerson.Actor(
        id = id,
        name = name,
        profilePath = profilePath,
        character = character
    )

    private fun NetworkEntertainmentPerson.Creator.toUICreator(): UIEntertainmentPerson.Creator = UIEntertainmentPerson.Creator(
        id = id,
        name = name,
        profilePath = profilePath,
        job = job
    )

    private companion object {
        const val IMAGE_SIZE_KF = 2
    }
}
