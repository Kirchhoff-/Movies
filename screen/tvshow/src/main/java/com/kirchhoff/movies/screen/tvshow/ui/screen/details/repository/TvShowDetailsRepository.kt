package com.kirchhoff.movies.screen.tvshow.ui.screen.details.repository

import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.tv.NetworkTvDetails
import com.kirchhoff.movies.networkdata.main.NetworkTv
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.network.TvShowDetailsService

internal interface ITvShowDetailsRepository {
    suspend fun fetchSimilar(id: TvId, page: Int): Result<NetworkPaginated<NetworkTv>>
    suspend fun fetchDetails(id: TvId): Result<NetworkTvDetails>
    suspend fun fetchCredits(id: TvId): Result<NetworkEntertainmentCredits>
}

internal class TvShowDetailsRepository(
    private val tvShowDetailsService: TvShowDetailsService
) : BaseRepository(), ITvShowDetailsRepository {

    override suspend fun fetchSimilar(id: TvId, page: Int): Result<NetworkPaginated<NetworkTv>> = apiCall {
        tvShowDetailsService.fetchSimilarTvShows(id.value, page)
    }

    override suspend fun fetchDetails(id: TvId): Result<NetworkTvDetails> = apiCall { tvShowDetailsService.fetchDetails(id.value) }

    override suspend fun fetchCredits(id: TvId): Result<NetworkEntertainmentCredits> =
        apiCall { tvShowDetailsService.fetchCredits(id.value) }
}
