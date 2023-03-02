package com.kirchhoff.movies.screen.review.repository

import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.review.data.UIReview

interface IReviewRepository {
    suspend fun fetchMovieReviews(movieId: Int, page: Int): Result<UIPaginated<UIReview>>
    suspend fun fetchTvReviews(tvId: Int, page: Int): Result<UIPaginated<UIReview>>
}
