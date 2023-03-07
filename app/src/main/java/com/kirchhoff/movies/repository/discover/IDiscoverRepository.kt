package com.kirchhoff.movies.repository.discover

import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.data.ui.main.UITv

interface IDiscoverRepository {
    suspend fun fetchMovies(page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchTvs(page: Int): Result<UIPaginated<UITv>>
}
