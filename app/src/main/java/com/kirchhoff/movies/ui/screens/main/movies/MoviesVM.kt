package com.kirchhoff.movies.ui.screens.main.movies

import com.kirchhoff.movies.data.ui.main.UIDiscoverMovies
import com.kirchhoff.movies.repository.discover.IDiscoverRepository
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenVM

class MoviesVM(private val discoverRepository: IDiscoverRepository) :
    PaginatedScreenVM<UIDiscoverMovies>() {
    override suspend fun loadData(page: Int, dataId: Int) = discoverRepository.fetchMovies(page)
}
