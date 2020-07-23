package com.kirchhoff.movies.ui.screens.similar.movie

import com.kirchhoff.movies.data.responses.DiscoverMoviesResponse
import com.kirchhoff.movies.repository.movie.IMovieRepository
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenVM

class SimilarMoviesVM(private val movieRepository: IMovieRepository) : PaginatedScreenVM<DiscoverMoviesResponse>() {
    override suspend fun loadData(page: Int, dataId: Int) = movieRepository.fetchSimilarMoviesList(dataId, page)
}
