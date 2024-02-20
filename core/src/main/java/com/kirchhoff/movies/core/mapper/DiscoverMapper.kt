package com.kirchhoff.movies.core.mapper

import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkTv

class DiscoverMapper : BaseMapper(), IDiscoverMapper {

    override fun createUIDiscoverTvList(discoverTvs: Result<NetworkPaginated<NetworkTv>>): Result<UIPaginated<UITv>> =
        when (discoverTvs) {
            is Result.Success -> Result.Success(createUIDiscoverTvs(discoverTvs.data))
            else -> mapErrorOrException(discoverTvs)
        }

    private fun createUIDiscoverTvs(response: NetworkPaginated<NetworkTv>) =
        UIPaginated(
            response.page,
            response.results.map { createUITv(it) },
            response.totalPages
        )

    private fun createUITv(tv: NetworkTv) =
        UITv(
            tv.id,
            tv.name,
            tv.posterPath,
            tv.backdropPath,
            tv.voteAverage
        )
}
