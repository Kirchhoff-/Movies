package com.kirchhoff.movies.repository.discover

import com.kirchhoff.movies.data.DiscoverMoviesResponse
import com.kirchhoff.movies.data.DiscoverTvsResponse
import com.kirchhoff.movies.repository.Result

interface IDiscoverRepository {
    suspend fun fetchMovies(page: Int): Result<DiscoverMoviesResponse>
    suspend fun fetchTvs(page: Int): Result<DiscoverTvsResponse>
}
