package com.kirchhoff.movies.screen.tvshow.mapper.details

import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.core.mapper.core.ICoreMapper
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.details.tv.NetworkTvDetails
import com.kirchhoff.movies.screen.tvshow.data.UITvShowDetails

internal class TvShowDetailsMapper(private val coreMapper: ICoreMapper) :
    BaseMapper(),
    ITvShowDetailsMapper {

    override fun createUITvDetails(tvDetailsResult: Result<NetworkTvDetails>): Result<UITvShowDetails> =
        when (tvDetailsResult) {
            is Result.Success -> Result.Success(createUITvDetails(tvDetailsResult.data))
            else -> mapErrorOrException(tvDetailsResult)
        }

    override fun createUIEntertainmentCredits(tvCreditsResult: Result<NetworkEntertainmentCredits>): Result<UIEntertainmentCredits> =
        when (tvCreditsResult) {
            is Result.Success -> Result.Success(
                coreMapper.createUIEntertainmentCredits(
                    tvCreditsResult.data
                )
            )

            else -> mapErrorOrException(tvCreditsResult)
        }

    private fun createUITvDetails(tvDetails: NetworkTvDetails) =
        UITvShowDetails(
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
