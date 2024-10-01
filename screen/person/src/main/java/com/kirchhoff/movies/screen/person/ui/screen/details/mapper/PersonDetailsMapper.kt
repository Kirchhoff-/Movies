package com.kirchhoff.movies.screen.person.ui.screen.details.mapper

import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCastCredit
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCredits
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCrewCredit
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonDetails
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonImage
import com.kirchhoff.movies.screen.person.data.UIMediaType
import com.kirchhoff.movies.screen.person.data.UIPersonCredit
import com.kirchhoff.movies.screen.person.data.UIPersonCredits
import com.kirchhoff.movies.screen.person.data.UIPersonDetails
import com.kirchhoff.movies.screen.person.data.UIPersonImage

internal interface IPersonDetailsMapper {
    fun createPersonDetails(personDetails: NetworkPersonDetails): UIPersonDetails
    fun createCredits(credits: NetworkPersonCredits): UIPersonCredits
    fun createPersonImage(image: NetworkPersonImage): UIPersonImage
}

internal class PersonDetailsMapper : IPersonDetailsMapper {

    override fun createPersonDetails(personDetails: NetworkPersonDetails): UIPersonDetails = UIPersonDetails(
        birthday = personDetails.birthday,
        placeOfBirth = personDetails.placeOfBirth,
        biography = personDetails.biography,
        alsoKnownAs = personDetails.alsoKnownAs
    )

    override fun createCredits(credits: NetworkPersonCredits): UIPersonCredits = UIPersonCredits(
        cast = credits.cast?.map { it.toUIPersonCreditActor() },
        crew = credits.crew?.map { it.toUIPersonCreator() }
    )

    override fun createPersonImage(image: NetworkPersonImage): UIPersonImage = UIPersonImage(image.filePath.orEmpty())

    private fun NetworkPersonCastCredit.toUIPersonCreditActor(): UIPersonCredit.Actor = UIPersonCredit.Actor(
        id = id,
        title = title ?: name ?: error("Wrong network data"),
        posterPath = posterPath,
        backdropPath = backdropPath,
        mediaType = mediaType.toMediaType(),
        character = character
    )

    private fun NetworkPersonCrewCredit.toUIPersonCreator(): UIPersonCredit.Creator = UIPersonCredit.Creator(
        id = id,
        title = title ?: name ?: error("Wrong network data"),
        posterPath = posterPath,
        backdropPath = backdropPath,
        mediaType = mediaType.toMediaType(),
        job = job
    )

    private fun String.toMediaType(): UIMediaType = when {
        equals(UIMediaType.MOVIE.name, true) -> UIMediaType.MOVIE
        equals(UIMediaType.TV.name, true) -> UIMediaType.TV
        else -> error("Unknown media type = $this")
    }
}
