package com.kirchhoff.movies.mapper.tv

import com.kirchhoff.movies.data.network.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.data.network.details.tv.NetworkTvDetails
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails
import com.kirchhoff.movies.repository.Result

interface ITvDetailsMapper {
    fun createUITvDetails(tvDetailsResult: Result<NetworkTvDetails>): Result<UITvDetails>
    fun createUIEntertainmentCredits(tvCreditsResult: Result<NetworkEntertainmentCredits>): Result<UIEntertainmentCredits>
}
