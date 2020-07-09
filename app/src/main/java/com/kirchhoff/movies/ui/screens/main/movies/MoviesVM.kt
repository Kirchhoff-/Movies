package com.kirchhoff.movies.ui.screens.main.movies

import com.kirchhoff.movies.data.responses.DiscoverMoviesResponse
import com.kirchhoff.movies.repository.discover.IDiscoverRepository
import com.kirchhoff.movies.ui.screens.main.MainScreenVM

class MoviesVM(private val discoverRepository: IDiscoverRepository) :
    MainScreenVM<DiscoverMoviesResponse>() {
    override suspend fun loadData(page: Int) = discoverRepository.fetchMovies(page)
}
