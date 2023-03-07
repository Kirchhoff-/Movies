package com.kirchhoff.movies.mapper.tv

import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.details.tv.NetworkTvDetails

interface ITvDetailsMapper {
    fun createUITvDetails(tvDetailsResult: Result<NetworkTvDetails>): Result<UITvDetails>
    fun createUIEntertainmentCredits(tvCreditsResult: Result<NetworkEntertainmentCredits>): Result<UIEntertainmentCredits>
}
