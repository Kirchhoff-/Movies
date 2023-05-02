package com.kirchhoff.movies.screen.country.ui.screen.movie.viewmodel

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenVM
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.country.usecase.IMoviesByCountryUseCase

class MoviesByCountryViewModel(
    private val countryId: String,
    private val moviesByCountryUseCase: IMoviesByCountryUseCase
) : PaginatedScreenVM<UIPaginated<UIMovie>>() {
    override suspend fun loadData(page: Int): Result<UIPaginated<UIMovie>> =
        moviesByCountryUseCase.fetchMovies(countryId, page)
}
