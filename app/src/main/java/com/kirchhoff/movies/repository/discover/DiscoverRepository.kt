package com.kirchhoff.movies.repository.discover

import com.kirchhoff.movies.data.responses.DiscoverMoviesResponse
import com.kirchhoff.movies.data.responses.DiscoverTvsResponse
import com.kirchhoff.movies.network.services.DiscoverService
import com.kirchhoff.movies.repository.BaseRepository
import com.kirchhoff.movies.repository.Result

class DiscoverRepository(private val discoverService: DiscoverService) : BaseRepository(),
    IDiscoverRepository {
    override suspend fun fetchMovies(page: Int): Result<DiscoverMoviesResponse> {
        return apiCall { discoverService.fetchDiscoverMovie(page) }
    }

    override suspend fun fetchTvs(page: Int): Result<DiscoverTvsResponse> {
        return apiCall { discoverService.fetchDiscoverTv(page) }
    }
}
