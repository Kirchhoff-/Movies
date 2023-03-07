package com.kirchhoff.movies.screen.review.ui.screen.list

import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenVM
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.review.data.UIReview
import com.kirchhoff.movies.screen.review.repository.IReviewRepository
import com.kirchhoff.movies.screen.review.ui.screen.ReviewType

class ReviewsListVM(
    private val dataId: Int,
    private val reviewType: ReviewType,
    private val reviewRepository: IReviewRepository
) : PaginatedScreenVM<UIPaginated<UIReview>>() {

    override suspend fun loadData(page: Int) = when (reviewType) {
        ReviewType.MOVIE -> reviewRepository.fetchMovieReviews(dataId, page)
        ReviewType.TV -> reviewRepository.fetchTvReviews(dataId, page)
    }
}
