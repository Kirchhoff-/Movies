package com.kirchhoff.movies.screen.person.ui.screen.details.mapper

import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCastCredit
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCredits
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCrewCredit
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonDetails
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonImage
import com.kirchhoff.movies.screen.person.data.PersonUIImage
import com.kirchhoff.movies.screen.person.ui.screen.details.model.PersonUICredit
import com.kirchhoff.movies.screen.person.ui.screen.details.model.PersonUICredits
import com.kirchhoff.movies.screen.person.ui.screen.details.model.PersonUIDetails
import com.kirchhoff.movies.screen.person.ui.screen.details.model.PersonUIMediaType

internal class PersonDetailsMapper {

    fun createPersonDetails(personDetails: NetworkPersonDetails): PersonUIDetails = PersonUIDetails(
        birthday = personDetails.birthday,
        placeOfBirth = personDetails.placeOfBirth,
        biography = personDetails.biography,
        alsoKnownAs = personDetails.alsoKnownAs,
        homepage = personDetails.homepage
    )

    fun createCredits(credits: NetworkPersonCredits): PersonUICredits = PersonUICredits(
        cast = credits.cast?.map { it.toUIPersonCreditActor() },
        crew = credits.crew?.map { it.toUIPersonCreator() }
    )

    fun createPersonImage(image: NetworkPersonImage): PersonUIImage = PersonUIImage(image.filePath.orEmpty())

    private fun NetworkPersonCastCredit.toUIPersonCreditActor(): PersonUICredit.Actor = PersonUICredit.Actor(
        id = id,
        title = title ?: name ?: error("Wrong network data"),
        posterPath = posterPath,
        backdropPath = backdropPath,
        mediaType = mediaType.toMediaType(),
        character = character
    )

    private fun NetworkPersonCrewCredit.toUIPersonCreator(): PersonUICredit.Creator = PersonUICredit.Creator(
        id = id,
        title = title ?: name ?: error("Wrong network data"),
        posterPath = posterPath,
        backdropPath = backdropPath,
        mediaType = mediaType.toMediaType(),
        job = job
    )

    private fun String.toMediaType(): PersonUIMediaType = when {
        equals(PersonUIMediaType.MOVIE.name, true) -> PersonUIMediaType.MOVIE
        equals(PersonUIMediaType.TV.name, true) -> PersonUIMediaType.TV
        else -> error("Unknown media type = $this")
    }
}
