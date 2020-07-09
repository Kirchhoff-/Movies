package com.kirchhoff.movies.repository.discover

import com.kirchhoff.movies.data.responses.DiscoverMoviesResponse
import com.kirchhoff.movies.data.responses.DiscoverTvsResponse
import com.kirchhoff.movies.repository.Result

interface IDiscoverRepository {
    suspend fun fetchMovies(page: Int): Result<DiscoverMoviesResponse>
    suspend fun fetchTvs(page: Int): Result<DiscoverTvsResponse>
}
