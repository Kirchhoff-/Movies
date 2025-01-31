package com.kirchhoff.movies.screen.tvshow.ui.screen.list.mapper

import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.data.ui.UITv
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkTv

internal interface ITvShowListMapper {
    fun createTvShowList(tvShowList: NetworkPaginated<NetworkTv>): UIPaginated<UITv>
}

internal class TvShowListMapper : ITvShowListMapper {

    override fun createTvShowList(tvShowList: NetworkPaginated<NetworkTv>): UIPaginated<UITv> = UIPaginated(
        page = tvShowList.page,
        results = tvShowList.results.map { it.toUITv() },
        totalPages = tvShowList.totalPages
    )

    private fun NetworkTv.toUITv(): UITv = UITv(
        id = TvId(id),
        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage
    )
}
