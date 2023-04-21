package com.kirchhoff.movies.ui.screens.main.movies

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenVM
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.repository.discover.IDiscoverRepository

class MoviesVM(private val discoverRepository: IDiscoverRepository) :
    PaginatedScreenVM<UIPaginated<UIMovie>>() {
    override suspend fun loadData(page: Int) = discoverRepository.fetchMovies(page)
}
