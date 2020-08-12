package com.kirchhoff.movies.repository.discover

import com.kirchhoff.movies.data.ui.main.UIDiscoverMovies
import com.kirchhoff.movies.data.ui.main.UIDiscoverTvs
import com.kirchhoff.movies.repository.Result

interface IDiscoverRepository {
    suspend fun fetchMovies(page: Int): Result<UIDiscoverMovies>
    suspend fun fetchTvs(page: Int): Result<UIDiscoverTvs>
}
