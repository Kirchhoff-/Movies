package com.kirchhoff.movies.mapper.person

import com.kirchhoff.movies.data.network.details.person.NetworkPersonCastCredit
import com.kirchhoff.movies.data.network.details.person.NetworkPersonCredits
import com.kirchhoff.movies.data.network.details.person.NetworkPersonCrewCredit
import com.kirchhoff.movies.data.network.details.person.NetworkPersonDetails
import com.kirchhoff.movies.data.ui.details.person.UIPersonCastCredit
import com.kirchhoff.movies.data.ui.details.person.UIPersonCredits
import com.kirchhoff.movies.data.ui.details.person.UIPersonCrewCredit
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
            personCredits.cast?.map { createUIPersonCastCredit(it) },
            personCredits.crew?.map { createUIPersonCrewCredit(it) }
        )

    private fun createUIPersonCastCredit(castCredit: NetworkPersonCastCredit) =
        UIPersonCastCredit(
            castCredit.title,
            castCredit.character,
            castCredit.poster_path
        )

    private fun createUIPersonCrewCredit(crewCredit: NetworkPersonCrewCredit) =
        UIPersonCrewCredit(
            crewCredit.title,
            crewCredit.job,
            crewCredit.poster_path
        )
}
