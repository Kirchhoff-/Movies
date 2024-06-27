package com.kirchhoff.movies.screen.tvshow.ui.screen.list.repository

import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkTv
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.network.TvShowListService

internal interface ITvShowListRepository {
    suspend fun similar(id: TvId, page: Int): Result<NetworkPaginated<NetworkTv>>
    suspend fun airingToday(page: Int): Result<NetworkPaginated<NetworkTv>>
    suspend fun onTheAir(page: Int): Result<NetworkPaginated<NetworkTv>>
    suspend fun popular(page: Int): Result<NetworkPaginated<NetworkTv>>
    suspend fun topRated(page: Int): Result<NetworkPaginated<NetworkTv>>
}

internal class TvShowListRepository(private val tvShowListService: TvShowListService) : BaseRepository(), ITvShowListRepository {

    override suspend fun similar(id: TvId, page: Int): Result<NetworkPaginated<NetworkTv>> =
        apiCall { tvShowListService.fetchSimilarTvShows(id.value, page) }

    override suspend fun airingToday(page: Int): Result<NetworkPaginated<NetworkTv>> = apiCall { tvShowListService.fetchAiringToday(page) }

    override suspend fun onTheAir(page: Int): Result<NetworkPaginated<NetworkTv>> = apiCall { tvShowListService.fetchOnTheAir(page) }

    override suspend fun popular(page: Int): Result<NetworkPaginated<NetworkTv>> = apiCall { tvShowListService.fetchPopular(page) }

    override suspend fun topRated(page: Int): Result<NetworkPaginated<NetworkTv>> = apiCall { tvShowListService.fetchTopRated(page) }
}
