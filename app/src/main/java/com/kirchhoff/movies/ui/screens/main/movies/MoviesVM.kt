package com.kirchhoff.movies.ui.screens.main.movies

import com.kirchhoff.movies.data.responses.DiscoverMoviesResponse
import com.kirchhoff.movies.repository.discover.IDiscoverRepository
import com.kirchhoff.movies.ui.screens.PaginatedScreenVM

class MoviesVM(private val discoverRepository: IDiscoverRepository) :
    PaginatedScreenVM<DiscoverMoviesResponse>() {
    override suspend fun loadData(page: Int, dataId: Int) = discoverRepository.fetchMovies(page)
}
