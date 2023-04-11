package com.kirchhoff.movies.screen.review.repository

import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.review.NetworkReview

internal interface IReviewRepository {
    suspend fun fetchMovieReviews(movieId: Int, page: Int): Result<NetworkPaginated<NetworkReview>>
    suspend fun fetchTvReviews(tvId: Int, page: Int): Result<NetworkPaginated<NetworkReview>>
}
