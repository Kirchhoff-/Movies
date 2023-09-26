package com.kirchhoff.movies.screen.movie.ui.screen.country

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenViewModel
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository

class MovieCountryViewModel(
    private val countryId: String,
    private val movieRepository: IMovieRepository
) : PaginatedScreenViewModel<UIPaginated<UIMovie>>() {
    override suspend fun loadData(page: Int): Result<UIPaginated<UIMovie>> =
        movieRepository.fetchByCountry(countryId, page)
}
