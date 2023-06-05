package com.kirchhoff.movies.screen.tvshow.repository

import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.mapper.IDiscoverMapper
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.tvshow.data.UITvShowDetails
import com.kirchhoff.movies.screen.tvshow.mapper.details.ITvShowDetailsMapper
import com.kirchhoff.movies.screen.tvshow.network.TvShowService

class TvShowRepository(
    private val tvService: TvShowService,
    private val tvDetailsMapper: ITvShowDetailsMapper,
    private val discoverMapper: IDiscoverMapper
) : BaseRepository(), ITvShowRepository {

    override suspend fun fetchDiscoverList(page: Int): Result<UIPaginated<UITv>> =
        discoverMapper.createUIDiscoverTvList(apiCall {
            tvService.fetchDiscoverList(page)
        })

    override suspend fun fetchSimilarTvShows(tvId: Int, page: Int): Result<UIPaginated<UITv>> =
        discoverMapper.createUIDiscoverTvList(apiCall {
            tvService.fetchSimilarTvShows(tvId, page)
        })

    override suspend fun fetchDetails(tvId: Int): Result<UITvShowDetails> =
        tvDetailsMapper.createUITvDetails(apiCall {
            tvService.fetchDetails(tvId)
        })

    override suspend fun fetchCredits(tvId: Int): Result<UIEntertainmentCredits> =
        tvDetailsMapper.createUIEntertainmentCredits(apiCall {
            tvService.fetchCredits(tvId)
        })
}
