package com.kirchhoff.movies.mapper.mapper

import com.kirchhoff.movies.data.network.details.review.NetworkReview
import com.kirchhoff.movies.data.network.details.review.NetworkReviewsListResponse
import com.kirchhoff.movies.data.ui.details.review.UIReview
import com.kirchhoff.movies.data.ui.details.review.UIReviewsListResponse
import com.kirchhoff.movies.mapper.BaseMapper
import com.kirchhoff.movies.repository.Result

class ReviewListMapper : BaseMapper(), IReviewListMapper {

    override fun createUIReviewList(reviewsListResponse: Result<NetworkReviewsListResponse>): Result<UIReviewsListResponse> =
        when (reviewsListResponse) {
            is Result.Success -> Result.Success(createUIReviewResponse(reviewsListResponse.data))
            else -> mapErrorOrException(reviewsListResponse)
        }

    private fun createUIReviewResponse(response: NetworkReviewsListResponse) =
        UIReviewsListResponse(
            response.page,
            response.results.map { createUIReview(it) },
            response.total_pages
        )

    private fun createUIReview(networkReview: NetworkReview) =
        UIReview(networkReview.id, networkReview.author, networkReview.content)
}
