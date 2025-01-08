package com.kirchhoff.movies.screen.tvshow.ui.screen.discover.mapper

import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.data.ui.UITv
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkTv

internal interface ITvShowDiscoverMapper {
    fun mapTvShowList(tvShowList: NetworkPaginated<NetworkTv>): List<UITv>
}

internal class TvShowDiscoverMapper : ITvShowDiscoverMapper {
    override fun mapTvShowList(tvShowList: NetworkPaginated<NetworkTv>): List<UITv> = tvShowList.results.map { it.toUITv() }

    private fun NetworkTv.toUITv(): UITv = UITv(
        id = TvId(id),
        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage
    )
}
