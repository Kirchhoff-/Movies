package com.kirchhoff.movies.screen.similar.repository

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated

interface ISimilarRepository {
    suspend fun fetchMovies(movieId: Int, page: Int): Result<UIPaginated<UIMovie>>
}
