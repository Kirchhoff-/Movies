package com.kirchhoff.movies.screen.movie.repository

import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.movie.data.UIMovieDetails
import com.kirchhoff.movies.screen.movie.data.UITrailersList

interface IMovieRepository {
    suspend fun fetchDiscoverList(page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchDetails(movieId: Int): Result<UIMovieDetails>
    suspend fun fetchSimilarMovies(movieId: Int, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchByCountry(countryId: String, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchTrailersList(movieId: Int): Result<UITrailersList>
    suspend fun fetchMovieCredits(movieId: Int): Result<UIEntertainmentCredits>
}
