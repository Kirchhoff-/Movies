package com.kirchhoff.movies.repository.discover

import com.kirchhoff.movies.data.ui.core.UIPaginated
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.repository.Result

interface IDiscoverRepository {
    suspend fun fetchMovies(page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchTvs(page: Int): Result<UIPaginated<UITv>>
}
