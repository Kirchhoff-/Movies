package com.kirchhoff.movies.screen.movie.ui.screen.similar

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenVM
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository

class MovieSimilarViewModel(
    private val movieId: Int,
    private val movieRepository: IMovieRepository
) : PaginatedScreenVM<UIPaginated<UIMovie>>() {
    override suspend fun loadData(page: Int): Result<UIPaginated<UIMovie>> =
        movieRepository.fetchSimilarMovies(movieId, page)
}
