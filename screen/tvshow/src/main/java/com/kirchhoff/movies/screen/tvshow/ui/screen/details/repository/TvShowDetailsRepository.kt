package com.kirchhoff.movies.screen.tvshow.ui.screen.details.repository

import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.tv.NetworkTvDetails
import com.kirchhoff.movies.networkdata.main.NetworkTv
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.network.TvShowDetailsService

internal class TvShowDetailsRepository(private val tvShowDetailsService: TvShowDetailsService) : BaseRepository() {

    suspend fun similar(id: TvId, page: Int): RepositoryResult<NetworkPaginated<NetworkTv>> = apiCall {
        tvShowDetailsService.fetchSimilarTvShows(id.value, page)
    }

    suspend fun details(id: TvId): RepositoryResult<NetworkTvDetails> = apiCall { tvShowDetailsService.fetchDetails(id.value) }

    suspend fun credits(id: TvId): RepositoryResult<NetworkEntertainmentCredits> = apiCall { tvShowDetailsService.fetchCredits(id.value) }
}
