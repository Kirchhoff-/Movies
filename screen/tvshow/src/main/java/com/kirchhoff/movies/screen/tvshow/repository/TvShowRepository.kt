package com.kirchhoff.movies.screen.tvshow.repository

import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.tvshow.data.UITvShowInfo
import com.kirchhoff.movies.screen.tvshow.mapper.ITvShowDetailsMapper
import com.kirchhoff.movies.screen.tvshow.mapper.ITvShowListMapper
import com.kirchhoff.movies.screen.tvshow.network.TvShowService
import com.kirchhoff.movies.storage.tvshow.IStorageTvShow

internal interface ITvShowRepository {
    suspend fun fetchDiscoverList(page: Int): Result<UIPaginated<UITv>>
    suspend fun fetchSimilarTvShows(id: TvId, page: Int): Result<UIPaginated<UITv>>
    suspend fun fetchDetails(id: TvId): Result<UITvShowInfo>
    suspend fun fetchCredits(id: TvId): Result<UIEntertainmentCredits>
}

internal class TvShowRepository(
    private val tvService: TvShowService,
    private val tvDetailsMapper: ITvShowDetailsMapper,
    private val tvShowListMapper: ITvShowListMapper,
    private val tvShowStorage: IStorageTvShow
) : BaseRepository(), ITvShowRepository {

    override suspend fun fetchDiscoverList(page: Int): Result<UIPaginated<UITv>> {
        val result = apiCall { tvService.fetchDiscoverList(page) }

        if (result is Result.Success) {
            result.data.results.forEach { tvShowStorage.updateInfo(it) }
        }

        return tvShowListMapper.createTvShowList(result)
    }

    override suspend fun fetchSimilarTvShows(id: TvId, page: Int): Result<UIPaginated<UITv>> =
        tvShowListMapper.createTvShowList(
            apiCall {
                tvService.fetchSimilarTvShows(id.value, page)
            }
        )

    override suspend fun fetchDetails(id: TvId): Result<UITvShowInfo> =
        tvDetailsMapper.createUITvDetails(
            apiCall {
                tvService.fetchDetails(id.value)
            }
        )

    override suspend fun fetchCredits(id: TvId): Result<UIEntertainmentCredits> =
        tvDetailsMapper.createUIEntertainmentCredits(
            apiCall {
                tvService.fetchCredits(id.value)
            }
        )
}
