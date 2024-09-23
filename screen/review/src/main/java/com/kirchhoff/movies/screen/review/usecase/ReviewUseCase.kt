package com.kirchhoff.movies.screen.review.usecase

import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.review.data.UIReview
import com.kirchhoff.movies.screen.review.mapper.IReviewListMapper
import com.kirchhoff.movies.screen.review.repository.IReviewRepository
import com.kirchhoff.movies.storage.movie.IStorageMovie
import com.kirchhoff.movies.storage.tvshow.IStorageTvShow

internal interface IReviewUseCase {
    suspend fun fetchMovieReviews(movieId: Int, page: Int): kotlin.Result<UIPaginated<UIReview>>
    suspend fun fetchTvReviews(tvId: Int, page: Int): kotlin.Result<UIPaginated<UIReview>>
    fun movieTitle(movieId: Int): String
    fun tvShowTitle(tvShowId: Int): String
}

internal class ReviewUseCase(
    private val reviewRepository: IReviewRepository,
    private val reviewMapper: IReviewListMapper,
    private val movieStorage: IStorageMovie,
    private val tvShowStorage: IStorageTvShow
) : IReviewUseCase {

    override suspend fun fetchMovieReviews(movieId: Int, page: Int): kotlin.Result<UIPaginated<UIReview>> =
        when (val movieReviews = reviewRepository.fetchMovieReviews(movieId, page)) {
            is Result.Success -> kotlin.Result.success(reviewMapper.createUIReviewList(movieReviews.data))
            else -> kotlin.Result.failure(Exception("Can't get info"))
        }

    override suspend fun fetchTvReviews(tvId: Int, page: Int): kotlin.Result<UIPaginated<UIReview>> =
        when (val movieReviews = reviewRepository.fetchTvReviews(tvId, page)) {
            is Result.Success -> kotlin.Result.success(reviewMapper.createUIReviewList(movieReviews.data))
            else -> kotlin.Result.failure(Exception("Can't get info"))
        }

    override fun movieTitle(movieId: Int): String =
        movieStorage.info(movieId)?.title ?: error("Can't get title for movie with id = $movieId")

    override fun tvShowTitle(tvShowId: Int): String =
        tvShowStorage.info(tvShowId)?.name ?: error("Can't get title for tv show with id = $tvShowId")
}
