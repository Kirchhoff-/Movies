package com.kirchhoff.movies.ui.screens.similar.movie

import com.kirchhoff.movies.data.ui.main.UIDiscoverMovies
import com.kirchhoff.movies.repository.movie.IMovieRepository
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenVM

class SimilarMoviesVM(private val movieRepository: IMovieRepository) : PaginatedScreenVM<UIDiscoverMovies>() {
    override suspend fun loadData(page: Int, dataId: Int) = movieRepository.fetchSimilarMoviesList(dataId, page)
}
