package com.kirchhoff.movies.screen.tvshow.mapper.details

import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.details.tv.NetworkTvDetails
import com.kirchhoff.movies.screen.tvshow.data.UITvShowInfo

internal interface ITvShowDetailsMapper {
    fun createUITvDetails(tvDetailsResult: Result<NetworkTvDetails>): Result<UITvShowInfo>
    fun createUIEntertainmentCredits(tvCreditsResult: Result<NetworkEntertainmentCredits>): Result<UIEntertainmentCredits>
}
