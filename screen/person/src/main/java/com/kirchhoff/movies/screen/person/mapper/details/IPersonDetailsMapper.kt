package com.kirchhoff.movies.screen.person.mapper.details

import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCredits
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonDetails
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonImages
import com.kirchhoff.movies.screen.person.data.UIPersonCredits
import com.kirchhoff.movies.screen.person.data.UIPersonDetails
import com.kirchhoff.movies.screen.person.data.UIPersonImage

internal interface IPersonDetailsMapper {
    fun createUIPersonDetails(personDetailsResult: Result<NetworkPersonDetails>): Result<UIPersonDetails>
    fun createUIPersonCredits(personCreditsResult: Result<NetworkPersonCredits>): Result<UIPersonCredits>
    fun createUIPersonImages(personImagesResult: Result<NetworkPersonImages>): Result<List<UIPersonImage>>
}
