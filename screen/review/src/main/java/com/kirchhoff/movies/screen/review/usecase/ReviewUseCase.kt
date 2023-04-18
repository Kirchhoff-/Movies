package com.kirchhoff.movies.screen.review.usecase

import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.review.data.UIReview
import com.kirchhoff.movies.screen.review.mapper.IReviewListMapper
import com.kirchhoff.movies.screen.review.repository.IReviewRepository

internal class ReviewUseCase(
    private val reviewRepository: IReviewRepository,
    private val reviewMapper: IReviewListMapper
) : IReviewUseCase {

    override suspend fun fetchMovieReviews(movieId: Int, page: Int): Result<UIPaginated<UIReview>> =
        reviewMapper.createUIReviewList(reviewRepository.fetchMovieReviews(movieId, page))

    override suspend fun fetchTvReviews(tvId: Int, page: Int): Result<UIPaginated<UIReview>> =
        reviewMapper.createUIReviewList(reviewRepository.fetchTvReviews(tvId, page))
}
