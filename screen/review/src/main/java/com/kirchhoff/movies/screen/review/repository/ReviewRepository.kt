package com.kirchhoff.movies.screen.review.repository

import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.review.data.UIReview
import com.kirchhoff.movies.screen.review.mapper.IReviewListMapper
import com.kirchhoff.movies.screen.review.network.ReviewService

internal class ReviewRepository(
    private val reviewService: ReviewService,
    private val reviewMapper: IReviewListMapper
) : BaseRepository(), IReviewRepository {

    override suspend fun fetchMovieReviews(movieId: Int, page: Int): Result<UIPaginated<UIReview>> =
        reviewMapper.createUIReviewList(apiCall { reviewService.movieReviews(movieId, page) })

    override suspend fun fetchTvReviews(tvId: Int, page: Int): Result<UIPaginated<UIReview>> =
        reviewMapper.createUIReviewList(apiCall { reviewService.tvReviews(tvId, page) })
}
