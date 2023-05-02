package com.kirchhoff.movies.screen.person.mapper.details

import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCastCredit
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCredits
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCrewCredit
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonDetails
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonImage
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonImages
import com.kirchhoff.movies.screen.person.data.UIMediaType
import com.kirchhoff.movies.screen.person.data.UIPersonCredit
import com.kirchhoff.movies.screen.person.data.UIPersonCredits
import com.kirchhoff.movies.screen.person.data.UIPersonDetails
import com.kirchhoff.movies.screen.person.data.UIPersonImage

class PersonDetailsMapper : BaseMapper(),
    IPersonDetailsMapper {

    override fun createUIPersonDetails(personDetailsResult: Result<NetworkPersonDetails>): Result<UIPersonDetails> =
        when (personDetailsResult) {
            is Result.Success -> Result.Success(createUIPersonDetails(personDetailsResult.data))
            else -> mapErrorOrException(personDetailsResult)
        }

    override fun createUIPersonCredits(personCreditsResult: Result<NetworkPersonCredits>): Result<UIPersonCredits> =
        when (personCreditsResult) {
            is Result.Success -> Result.Success(createUIPersonCredits(personCreditsResult.data))
            else -> mapErrorOrException(personCreditsResult)
        }

    override fun createUIPersonImages(personImagesResult: Result<NetworkPersonImages>): Result<List<UIPersonImage>> =
        when (personImagesResult) {
            is Result.Success -> Result.Success(
                personImagesResult.data.images
                    .filter { it.filePath != null }
                    .map { createPersonImage(it) }
            )
            else -> mapErrorOrException(personImagesResult)
        }

    private fun createUIPersonDetails(personDetails: NetworkPersonDetails) =
        UIPersonDetails(
            personDetails.birthday,
            personDetails.placeOfBirth,
            personDetails.biography,
            personDetails.alsoKnownAs
        )

    private fun createUIPersonCredits(personCredits: NetworkPersonCredits) =
        UIPersonCredits(
            personCredits.cast?.map { createUIPersonActor(it) },
            personCredits.crew?.map { createUIPersonCreator(it) }
        )

    private fun createUIPersonActor(castCredit: NetworkPersonCastCredit) =
        UIPersonCredit.Actor(
            castCredit.id,
            castCredit.title,
            castCredit.posterPath,
            castCredit.backdropPath,
            createMediaType(castCredit.mediaType),
            castCredit.character
        )

    private fun createUIPersonCreator(crewCredit: NetworkPersonCrewCredit) =
        UIPersonCredit.Creator(
            crewCredit.id,
            crewCredit.title,
            crewCredit.posterPath,
            crewCredit.backdropPath,
            createMediaType(crewCredit.mediaType),
            crewCredit.job
        )

    private fun createMediaType(value: String) =
        when {
            value.equals(UIMediaType.MOVIE.name, true) -> UIMediaType.MOVIE
            value.equals(UIMediaType.TV.name, true) -> UIMediaType.TV
            else -> error("Unknown media type = $value")
        }

    private fun createPersonImage(image: NetworkPersonImage) =
        UIPersonImage(image.filePath.orEmpty())
}
