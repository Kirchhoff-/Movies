package com.kirchhoff.movies.repository.movie

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.details.movie.UIMovieDetails
import com.kirchhoff.movies.data.ui.details.movie.UITrailersList

interface IMovieRepository {
    suspend fun fetchDetails(movieId: Int): Result<UIMovieDetails>
    suspend fun fetchSimilarMoviesList(movieId: Int, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchTrailersList(movieId: Int): Result<UITrailersList>
    suspend fun fetchMovieCredits(movieId: Int): Result<UIEntertainmentCredits>
}
