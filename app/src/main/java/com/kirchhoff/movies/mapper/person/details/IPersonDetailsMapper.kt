package com.kirchhoff.movies.mapper.person.details

import com.kirchhoff.movies.data.ui.details.person.UIPersonCredits
import com.kirchhoff.movies.data.ui.details.person.UIPersonDetails
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCredits
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonDetails
import com.kirchhoff.movies.repository.Result

interface IPersonDetailsMapper {
    fun createUIPersonDetails(personDetailsResult: Result<NetworkPersonDetails>): Result<UIPersonDetails>
    fun createUIPersonCredits(personCreditsResult: Result<NetworkPersonCredits>): Result<UIPersonCredits>
}
