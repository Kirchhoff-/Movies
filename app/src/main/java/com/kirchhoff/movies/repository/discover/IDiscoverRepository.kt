package com.kirchhoff.movies.repository.discover

import com.kirchhoff.movies.data.ui.core.PaginatedData
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.repository.Result

interface IDiscoverRepository {
    suspend fun fetchMovies(page: Int): Result<PaginatedData<UIMovie>>
    suspend fun fetchTvs(page: Int): Result<PaginatedData<UITv>>
}
