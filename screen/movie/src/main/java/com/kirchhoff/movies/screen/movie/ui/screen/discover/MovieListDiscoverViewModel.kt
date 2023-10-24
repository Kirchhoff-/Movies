package com.kirchhoff.movies.screen.movie.ui.screen.discover

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenViewModel
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository

internal class MovieListDiscoverViewModel(private val movieRepository: IMovieRepository) :
    PaginatedScreenViewModel<UIPaginated<UIMovie>>() {
    override suspend fun loadData(page: Int) = movieRepository.fetchDiscoverList(page)
}
