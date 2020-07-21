package com.kirchhoff.movies.repository.movie

import com.kirchhoff.movies.data.responses.MovieDetails
import com.kirchhoff.movies.data.responses.ReviewsListResponse
import com.kirchhoff.movies.repository.Result

interface IMovieRepository {
    suspend fun fetchDetails(movieId: Int): Result<MovieDetails>
    suspend fun fetchReviewsList(movieId: Int, page: Int): Result<ReviewsListResponse>
}
