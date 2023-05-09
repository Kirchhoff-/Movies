package com.kirchhoff.movies.repository.movie

import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.data.ui.details.movie.UIMovieDetails
import com.kirchhoff.movies.data.ui.details.movie.UITrailersList

interface IMovieRepository {
    suspend fun fetchDetails(movieId: Int): Result<UIMovieDetails>
    suspend fun fetchTrailersList(movieId: Int): Result<UITrailersList>
    suspend fun fetchMovieCredits(movieId: Int): Result<UIEntertainmentCredits>
}
