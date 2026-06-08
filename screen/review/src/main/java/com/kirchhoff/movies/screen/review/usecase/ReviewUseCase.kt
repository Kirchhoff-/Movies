package com.kirchhoff.movies.screen.review.usecase

import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.review.data.UIReview
import com.kirchhoff.movies.screen.review.mapper.ReviewListMapper
import com.kirchhoff.movies.screen.review.repository.ReviewRepository
import com.kirchhoff.movies.storage.movie.StorageMovie
import com.kirchhoff.movies.storage.tvshow.StorageTvShow

internal class ReviewUseCase(
    private val reviewRepository: ReviewRepository,
    private val reviewMapper: ReviewListMapper,
    private val movieStorage: StorageMovie,
    private val tvShowStorage: StorageTvShow
) {

    suspend fun fetchMovieReviews(movieId: Int, page: Int): Result<UIPaginated<UIReview>> =
        when (val movieReviews = reviewRepository.movieReviews(movieId, page)) {
            is RepositoryResult.Success -> Result.success(reviewMapper.createUIReviewList(movieReviews.data))
            else -> Result.failure(Exception("Can't get info"))
        }

    suspend fun fetchTvReviews(tvId: Int, page: Int): Result<UIPaginated<UIReview>> =
        when (val movieReviews = reviewRepository.rvReviews(tvId, page)) {
            is RepositoryResult.Success -> Result.success(reviewMapper.createUIReviewList(movieReviews.data))
            else -> Result.failure(Exception("Can't get info"))
        }

    fun movieTitle(movieId: Int): String =
        movieStorage.info(movieId)?.title ?: error("Can't get title for movie with id = $movieId")

    fun tvShowTitle(tvShowId: Int): String =
        tvShowStorage.info(tvShowId)?.name ?: error("Can't get title for tv show with id = $tvShowId")
}
