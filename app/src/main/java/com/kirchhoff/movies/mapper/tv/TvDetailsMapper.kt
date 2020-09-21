package com.kirchhoff.movies.mapper.tv

import com.kirchhoff.movies.data.network.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.data.network.details.tv.NetworkTvDetails
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails
import com.kirchhoff.movies.mapper.BaseMapper
import com.kirchhoff.movies.mapper.core.ICoreMapper
import com.kirchhoff.movies.repository.Result

class TvDetailsMapper(private val coreMapper: ICoreMapper) : BaseMapper(), ITvDetailsMapper {

    override fun createUITvDetails(tvDetailsResult: Result<NetworkTvDetails>): Result<UITvDetails> =
        when (tvDetailsResult) {
            is Result.Success -> Result.Success(createUITvDetails(tvDetailsResult.data))
            else -> mapErrorOrException(tvDetailsResult)
        }

    override fun createUIEntertainmentCredits(tvCreditsResult: Result<NetworkEntertainmentCredits>): Result<UIEntertainmentCredits> =
        when (tvCreditsResult) {
            is Result.Success -> Result.Success(coreMapper.createUIEntertainmentCredits(tvCreditsResult.data))
            else -> mapErrorOrException(tvCreditsResult)
        }

    private fun createUITvDetails(tvDetails: NetworkTvDetails) =
        UITvDetails(
            tvDetails.numberOfSeasons,
            tvDetails.numberOfEpisodes,
            tvDetails.overview,
            tvDetails.status,
            tvDetails.firstAirDate,
            tvDetails.voteCount,
            tvDetails.voteAverage,
            tvDetails.genres.map { coreMapper.createUIGenre(it) }
        )
}
