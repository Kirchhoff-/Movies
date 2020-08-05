package com.kirchhoff.movies.mapper.tv

import com.kirchhoff.movies.data.network.details.tv.NetworkTvCredits
import com.kirchhoff.movies.data.network.details.tv.NetworkTvDetails
import com.kirchhoff.movies.data.ui.details.tv.UITvCredits
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails
import com.kirchhoff.movies.repository.Result

interface ITvDetailsMapper {
    fun createUITvDetails(tvDetailsResult: Result<NetworkTvDetails>): Result<UITvDetails>
    fun createUITvCredits(tvCreditsResult: Result<NetworkTvCredits>): Result<UITvCredits>
}
