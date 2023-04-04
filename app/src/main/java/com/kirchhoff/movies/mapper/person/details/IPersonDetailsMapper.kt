package com.kirchhoff.movies.mapper.person.details

import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.data.ui.details.person.UIPersonCredits
import com.kirchhoff.movies.data.ui.details.person.UIPersonDetails
import com.kirchhoff.movies.data.ui.details.person.UIPersonImage
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCredits
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonDetails
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonImages

interface IPersonDetailsMapper {
    fun createUIPersonDetails(personDetailsResult: Result<NetworkPersonDetails>): Result<UIPersonDetails>
    fun createUIPersonCredits(personCreditsResult: Result<NetworkPersonCredits>): Result<UIPersonCredits>
    fun createUIPersonImages(personImagesResult: Result<NetworkPersonImages>): Result<List<UIPersonImage>>
}
