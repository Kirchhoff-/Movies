package com.kirchhoff.movies.screen.review.usecase

import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.review.data.UIReview

internal interface IReviewUseCase {
    suspend fun fetchMovieReviews(movieId: Int, page: Int): Result<UIPaginated<UIReview>>
    suspend fun fetchTvReviews(tvId: Int, page: Int): Result<UIPaginated<UIReview>>
    fun movieTitle(movieId: Int): String
    fun tvShowTitle(tvShowId: Int): String
}
