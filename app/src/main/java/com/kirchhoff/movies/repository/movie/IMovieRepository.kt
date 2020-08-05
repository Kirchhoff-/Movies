package com.kirchhoff.movies.repository.movie

import com.kirchhoff.movies.data.responses.DiscoverMoviesResponse
import com.kirchhoff.movies.data.responses.ReviewsListResponse
import com.kirchhoff.movies.data.responses.TrailersListResponse
import com.kirchhoff.movies.data.ui.details.movie.UIMovieCredits
import com.kirchhoff.movies.data.ui.details.movie.UIMovieDetails
import com.kirchhoff.movies.repository.Result

interface IMovieRepository {
    suspend fun fetchDetails(movieId: Int): Result<UIMovieDetails>
    suspend fun fetchReviewsList(movieId: Int, page: Int): Result<ReviewsListResponse>
    suspend fun fetchSimilarMoviesList(movieId: Int, page: Int): Result<DiscoverMoviesResponse>
    suspend fun fetchTrailersList(movieId: Int): Result<TrailersListResponse>
    suspend fun fetchMovieCredits(movieId: Int): Result<UIMovieCredits>
}
