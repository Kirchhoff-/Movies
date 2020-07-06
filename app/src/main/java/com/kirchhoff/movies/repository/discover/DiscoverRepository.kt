package com.kirchhoff.movies.repository.discover

import com.kirchhoff.movies.data.DiscoverMoviesResponse
import com.kirchhoff.movies.network.services.DiscoverService
import com.kirchhoff.movies.repository.BaseRepository
import com.kirchhoff.movies.repository.Result

class DiscoverRepository(private val discoverService: DiscoverService) : BaseRepository(),
    IDiscoverRepository {
    override suspend fun fetchMovies(page: Int): Result<DiscoverMoviesResponse> {
        return apiCall { discoverService.fetchDiscoverMovieAsync(page) }
    }
}
