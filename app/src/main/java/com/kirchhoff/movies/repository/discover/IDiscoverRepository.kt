package com.kirchhoff.movies.repository.discover

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated

interface IDiscoverRepository {
    suspend fun fetchMovies(page: Int): Result<UIPaginated<UIMovie>>
}
