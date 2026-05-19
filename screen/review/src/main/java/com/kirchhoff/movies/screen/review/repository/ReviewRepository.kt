package com.kirchhoff.movies.screen.review.repository

import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.review.NetworkReview
import com.kirchhoff.movies.screen.review.network.ReviewService

internal interface IReviewRepository {
    suspend fun movieReviews(movieId: Int, page: Int): RepositoryResult<NetworkPaginated<NetworkReview>>
    suspend fun rvReviews(tvId: Int, page: Int): RepositoryResult<NetworkPaginated<NetworkReview>>
}

internal class ReviewRepository(
    private val reviewService: ReviewService
) : BaseRepository(), IReviewRepository {

    override suspend fun movieReviews(
        movieId: Int,
        page: Int
    ): RepositoryResult<NetworkPaginated<NetworkReview>> =
        apiCall { reviewService.movieReviews(movieId, page) }

    override suspend fun rvReviews(
        tvId: Int,
        page: Int
    ): RepositoryResult<NetworkPaginated<NetworkReview>> =
        apiCall { reviewService.tvReviews(tvId, page) }
}
