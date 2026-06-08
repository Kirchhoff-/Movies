package com.kirchhoff.movies.screen.tvshow.ui.screen.list.repository

import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkTv
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.network.TvShowListService

internal class TvShowListRepository(private val tvShowListService: TvShowListService) : BaseRepository() {

    suspend fun similar(id: TvId, page: Int): RepositoryResult<NetworkPaginated<NetworkTv>> =
        apiCall { tvShowListService.fetchSimilarTvShows(id.value, page) }

    suspend fun airingToday(page: Int): RepositoryResult<NetworkPaginated<NetworkTv>> =
        apiCall { tvShowListService.fetchAiringToday(page) }

    suspend fun onTheAir(page: Int): RepositoryResult<NetworkPaginated<NetworkTv>> =
        apiCall { tvShowListService.fetchOnTheAir(page) }

    suspend fun popular(page: Int): RepositoryResult<NetworkPaginated<NetworkTv>> =
        apiCall { tvShowListService.fetchPopular(page) }

    suspend fun topRated(page: Int): RepositoryResult<NetworkPaginated<NetworkTv>> =
        apiCall { tvShowListService.fetchTopRated(page) }
}
