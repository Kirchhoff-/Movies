package com.kirchhoff.movies.ui.screens.similar.movie

import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenVM
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.repository.movie.IMovieRepository

class SimilarMoviesVM(
    private val movieId: Int,
    private val movieRepository: IMovieRepository
) : PaginatedScreenVM<UIPaginated<UIMovie>>() {
    override suspend fun loadData(page: Int) = movieRepository.fetchSimilarMoviesList(movieId, page)
}
