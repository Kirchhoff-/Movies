package com.kirchhoff.movies.screen.country.usecase

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.mapper.IDiscoverMapper
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.country.repository.IMoviesByCountryRepository

class MoviesByCountryUseCase(
    private val moviesByCountryRepository: IMoviesByCountryRepository,
    private val discoverMapper: IDiscoverMapper
) : IMoviesByCountryUseCase {
    override suspend fun fetchMovies(countryId: String, page: Int): Result<UIPaginated<UIMovie>> =
        discoverMapper.createUIDiscoverMovieList(
            moviesByCountryRepository.fetchMovies(
                countryId,
                page
            )
        )
}
