package com.kirchhoff.movies.screen.tvshow.repository

import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.tv.NetworkTvDetails
import com.kirchhoff.movies.networkdata.main.NetworkTv
import com.kirchhoff.movies.screen.tvshow.mapper.ITvShowListMapper
import com.kirchhoff.movies.screen.tvshow.network.TvShowService
import com.kirchhoff.movies.storage.tvshow.IStorageTvShow

internal interface ITvShowRepository {
    suspend fun fetchDiscoverList(page: Int): Result<UIPaginated<UITv>>
    suspend fun fetchSimilar(id: TvId, page: Int): Result<NetworkPaginated<NetworkTv>>
    suspend fun fetchSimilarTvShows(id: TvId, page: Int): Result<UIPaginated<UITv>>
    suspend fun fetchDetails(id: TvId): Result<NetworkTvDetails>
    suspend fun fetchCredits(id: TvId): Result<NetworkEntertainmentCredits>
}

internal class TvShowRepository(
    private val tvService: TvShowService,
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

    override suspend fun fetchSimilar(id: TvId, page: Int): Result<NetworkPaginated<NetworkTv>> = apiCall {
        tvService.fetchSimilarTvShows(id.value, page)
    }

    override suspend fun fetchSimilarTvShows(id: TvId, page: Int): Result<UIPaginated<UITv>> =
        tvShowListMapper.createTvShowList(
            apiCall {
                tvService.fetchSimilarTvShows(id.value, page)
            }
        )

    override suspend fun fetchDetails(id: TvId): Result<NetworkTvDetails> = apiCall { tvService.fetchDetails(id.value) }

    override suspend fun fetchCredits(id: TvId): Result<NetworkEntertainmentCredits> = apiCall { tvService.fetchCredits(id.value) }
}
