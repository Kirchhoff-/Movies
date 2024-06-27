package com.kirchhoff.movies.screen.tvshow.ui.screen.details.mapper

import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.core.mapper.ICoreMapper
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.tv.NetworkTvDetails
import com.kirchhoff.movies.networkdata.main.NetworkTv
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.model.TvShowDetailsInfo

internal interface ITvShowDetailsMapper {
    fun createTvShowList(tvShowResult: Result<NetworkPaginated<NetworkTv>>): Result<UIPaginated<UITv>>
    fun createUITvDetails(tvDetailsResult: Result<NetworkTvDetails>): Result<TvShowDetailsInfo>
    fun createUIEntertainmentCredits(tvCreditsResult: Result<NetworkEntertainmentCredits>): Result<UIEntertainmentCredits>
}

internal class TvShowDetailsMapper(private val coreMapper: ICoreMapper) :
    BaseMapper(),
    ITvShowDetailsMapper {

    override fun createTvShowList(tvShowResult: Result<NetworkPaginated<NetworkTv>>): Result<UIPaginated<UITv>> = when (tvShowResult) {
        is Result.Success -> Result.Success(tvShowResult.data.toUIPaginated())
        else -> mapErrorOrException(tvShowResult)
    }

    override fun createUITvDetails(tvDetailsResult: Result<NetworkTvDetails>): Result<TvShowDetailsInfo> =
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

    private fun NetworkPaginated<NetworkTv>.toUIPaginated(): UIPaginated<UITv> = UIPaginated(
        page = page,
        results = results.map { it.toUITv() },
        totalPages = totalPages
    )

    private fun NetworkTv.toUITv(): UITv = UITv(
        id = TvId(id),
        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage
    )

    private fun createUITvDetails(tvDetails: NetworkTvDetails) =
        TvShowDetailsInfo(
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
