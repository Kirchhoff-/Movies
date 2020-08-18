package com.kirchhoff.movies.ui.screens.similar.movie

import com.kirchhoff.movies.data.ui.core.PaginatedData
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.repository.movie.IMovieRepository
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenVM

class SimilarMoviesVM(private val movieRepository: IMovieRepository) : PaginatedScreenVM<PaginatedData<UIMovie>>() {
    override suspend fun loadData(page: Int, dataId: Int) = movieRepository.fetchSimilarMoviesList(dataId, page)
}
