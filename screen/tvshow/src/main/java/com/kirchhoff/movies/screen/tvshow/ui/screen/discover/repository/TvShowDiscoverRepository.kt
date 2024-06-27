package com.kirchhoff.movies.screen.tvshow.ui.screen.discover.repository

import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkTv
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.network.TvShowDiscoverService
import com.kirchhoff.movies.storage.tvshow.IStorageTvShow
import retrofit2.Response

internal interface ITvShowDiscoverRepository {
    suspend fun airingToday(): Result<NetworkPaginated<NetworkTv>>
    suspend fun onTheAir(): Result<NetworkPaginated<NetworkTv>>
    suspend fun popular(): Result<NetworkPaginated<NetworkTv>>
    suspend fun topRated(): Result<NetworkPaginated<NetworkTv>>
}

internal class TvShowDiscoverRepository(
    private val tvShowDiscoverService: TvShowDiscoverService,
    private val tvShowStorage: IStorageTvShow
) : BaseRepository(), ITvShowDiscoverRepository {

    override suspend fun airingToday(): Result<NetworkPaginated<NetworkTv>> =
        fetchTvShows { tvShowDiscoverService.fetchAiringToday() }

    override suspend fun onTheAir(): Result<NetworkPaginated<NetworkTv>> =
        fetchTvShows { tvShowDiscoverService.fetchOnTheAir() }

    override suspend fun popular(): Result<NetworkPaginated<NetworkTv>> =
        fetchTvShows { tvShowDiscoverService.fetchPopular() }

    override suspend fun topRated(): Result<NetworkPaginated<NetworkTv>> =
        fetchTvShows { tvShowDiscoverService.fetchTopRated() }

    private suspend fun fetchTvShows(call: suspend () -> Response<NetworkPaginated<NetworkTv>>): Result<NetworkPaginated<NetworkTv>> {
        val result = apiCall { call.invoke() }

        if (result is Result.Success) {
            result.data.results.forEach { tvShowStorage.updateInfo(it) }
        }

        return result
    }
}
