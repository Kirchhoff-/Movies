package com.kirchhoff.movies.mapper.person.details

import com.kirchhoff.movies.data.network.details.person.NetworkPersonCastCredit
import com.kirchhoff.movies.data.network.details.person.NetworkPersonCredits
import com.kirchhoff.movies.data.network.details.person.NetworkPersonCrewCredit
import com.kirchhoff.movies.data.network.details.person.NetworkPersonDetails
import com.kirchhoff.movies.data.ui.details.person.UIMediaType
import com.kirchhoff.movies.data.ui.details.person.UIPersonCredit
import com.kirchhoff.movies.data.ui.details.person.UIPersonCredits
import com.kirchhoff.movies.data.ui.details.person.UIPersonDetails
import com.kirchhoff.movies.mapper.BaseMapper
import com.kirchhoff.movies.repository.Result

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

    private fun createUIPersonDetails(personDetails: NetworkPersonDetails) =
        UIPersonDetails(
            personDetails.birthday,
            personDetails.place_of_birth,
            personDetails.biography,
            personDetails.also_known_as
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
            castCredit.poster_path,
            castCredit.backdrop_path,
            createMediaType(castCredit.media_type),
            castCredit.character
        )

    private fun createUIPersonCreator(crewCredit: NetworkPersonCrewCredit) =
        UIPersonCredit.Creator(
            crewCredit.id,
            crewCredit.title,
            crewCredit.poster_path,
            crewCredit.backdrop_path,
            createMediaType(crewCredit.media_type),
            crewCredit.job
        )

    private fun createMediaType(value: String) =
        when {
            value.equals(UIMediaType.MOVIE.name, true) -> UIMediaType.MOVIE
            value.equals(UIMediaType.TV.name, true) -> UIMediaType.TV
            else -> error("Unknown media type = $value")
        }
}
