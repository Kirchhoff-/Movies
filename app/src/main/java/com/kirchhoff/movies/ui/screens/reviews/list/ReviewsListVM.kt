package com.kirchhoff.movies.ui.screens.reviews.list

import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenVM
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.data.ui.details.review.UIReview
import com.kirchhoff.movies.repository.movie.IMovieRepository
import com.kirchhoff.movies.repository.tv.ITvRepository
import com.kirchhoff.movies.ui.screens.reviews.ReviewType

class ReviewsListVM(
    private val dataId: Int,
    private val reviewType: ReviewType,
    private val movieRepository: IMovieRepository,
    private val tvRepository: ITvRepository
) : PaginatedScreenVM<UIPaginated<UIReview>>() {

    override suspend fun loadData(page: Int) = when (reviewType) {
        ReviewType.MOVIE -> movieRepository.fetchReviewsList(dataId, page)
        ReviewType.TV -> tvRepository.fetchReviewsList(dataId, page)
    }
}
