package com.kirchhoff.movies.screen.similar.ui.screen.movie.viewmodel

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenVM
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.similar.repository.ISimilarRepository

class SimilarMoviesViewModel(
    private val movieId: Int,
    private val similarRepository: ISimilarRepository
) : PaginatedScreenVM<UIPaginated<UIMovie>>() {
    override suspend fun loadData(page: Int): Result<UIPaginated<UIMovie>> =
        similarRepository.fetchMovies(movieId, page)
}
