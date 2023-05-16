package com.kirchhoff.movies.screen.movie.ui.screen.list

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenVM
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository

class MovieListVM(private val movieRepository: IMovieRepository) :
    PaginatedScreenVM<UIPaginated<UIMovie>>() {
    override suspend fun loadData(page: Int) = movieRepository.fetchDiscoverList(page)
}
