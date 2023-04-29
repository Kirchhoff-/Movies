package com.kirchhoff.movies.screen.country.usecase

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated

interface IMoviesByCountryUseCase {
    suspend fun fetchMovies(countryId: String, page: Int): Result<UIPaginated<UIMovie>>
}
