package com.kirchhoff.movies.screen.tvshow.ui.screen.details.mapper

import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.data.ui.UITv
import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.core.mapper.ICoreMapper
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.tv.NetworkTvDetails
import com.kirchhoff.movies.networkdata.main.NetworkTv
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.model.TvShowDetails

internal interface ITvShowDetailsMapper {
    fun createTvShowList(tvShowResponse: NetworkPaginated<NetworkTv>): UIPaginated<UITv>
    fun createUITvDetails(tvDetails: NetworkTvDetails): TvShowDetails
}

internal class TvShowDetailsMapper(private val coreMapper: ICoreMapper) : BaseMapper(), ITvShowDetailsMapper {

    override fun createTvShowList(tvShowResponse: NetworkPaginated<NetworkTv>): UIPaginated<UITv> = UIPaginated(
        page = tvShowResponse.page,
        results = tvShowResponse.results.map { it.toUITv() },
        totalPages = tvShowResponse.totalPages
    )

    override fun createUITvDetails(tvDetails: NetworkTvDetails): TvShowDetails = tvDetails.toTvShowDetailsInfo()

    private fun NetworkTv.toUITv(): UITv = UITv(
        id = TvId(id),
        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage
    )

    private fun NetworkTvDetails.toTvShowDetailsInfo(): TvShowDetails = TvShowDetails(
        numberOfSeasons = numberOfSeasons,
        numberOfEpisodes = numberOfEpisodes,
        overview = overview,
        status = status,
        firstAirDate = firstAirDate,
        voteCount = voteCount,
        voteAverage = voteAverage,
        genres = genres.map { coreMapper.createUIGenre(it) }
    )
}
