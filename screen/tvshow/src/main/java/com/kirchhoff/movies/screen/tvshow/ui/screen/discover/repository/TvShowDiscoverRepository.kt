package com.kirchhoff.movies.screen.tvshow.ui.screen.discover.repository

import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkTv
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.network.TvShowDiscoverService
import com.kirchhoff.movies.storage.tvshow.StorageTvShow
import retrofit2.Response

internal class TvShowDiscoverRepository(
    private val tvShowDiscoverService: TvShowDiscoverService,
    private val tvShowStorage: StorageTvShow
) : BaseRepository() {

    suspend fun airingToday(): RepositoryResult<NetworkPaginated<NetworkTv>> =
        fetchTvShows { tvShowDiscoverService.fetchAiringToday() }

    suspend fun onTheAir(): RepositoryResult<NetworkPaginated<NetworkTv>> =
        fetchTvShows { tvShowDiscoverService.fetchOnTheAir() }

    suspend fun popular(): RepositoryResult<NetworkPaginated<NetworkTv>> =
        fetchTvShows { tvShowDiscoverService.fetchPopular() }

    suspend fun topRated(): RepositoryResult<NetworkPaginated<NetworkTv>> =
        fetchTvShows { tvShowDiscoverService.fetchTopRated() }

    private suspend fun fetchTvShows(
        call: suspend () -> Response<NetworkPaginated<NetworkTv>>
    ): RepositoryResult<NetworkPaginated<NetworkTv>> {
        val result = apiCall { call.invoke() }

        if (result is RepositoryResult.Success) {
            result.data.results.forEach { tvShowStorage.updateInfo(it) }
        }

        return result
    }
}
