package com.kirchhoff.movies.ui.screens.main.movies

import com.kirchhoff.movies.data.ui.core.PaginatedData
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.repository.discover.IDiscoverRepository
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenVM

class MoviesVM(private val discoverRepository: IDiscoverRepository) :
    PaginatedScreenVM<PaginatedData<UIMovie>>() {
    override suspend fun loadData(page: Int) = discoverRepository.fetchMovies(page)
}
