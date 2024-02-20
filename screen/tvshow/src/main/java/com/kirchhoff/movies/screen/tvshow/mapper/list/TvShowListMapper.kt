package com.kirchhoff.movies.screen.tvshow.mapper.list

import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkTv

internal class TvShowListMapper : BaseMapper(), ITvShowListMapper {

    override fun createTvShowList(tvShowResult: Result<NetworkPaginated<NetworkTv>>): Result<UIPaginated<UITv>> = when (tvShowResult) {
        is Result.Success -> Result.Success(tvShowResult.data.toUIPaginated())
        else -> mapErrorOrException(tvShowResult)
    }

    private fun NetworkPaginated<NetworkTv>.toUIPaginated(): UIPaginated<UITv> = UIPaginated(
        page = page,
        results = results.map { it.toUITv() },
        totalPages = totalPages
    )

    private fun NetworkTv.toUITv(): UITv = UITv(
        id = id,
        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage
    )
}
