package com.kirchhoff.movies.mapper.review

import com.kirchhoff.movies.data.ui.core.UIPaginated
import com.kirchhoff.movies.data.ui.details.review.UIReview
import com.kirchhoff.movies.mapper.BaseMapper
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.review.NetworkReview
import com.kirchhoff.movies.repository.Result

class ReviewListMapper : BaseMapper(), IReviewListMapper {

    override fun createUIReviewList(reviewsListResponse: Result<NetworkPaginated<NetworkReview>>): Result<UIPaginated<UIReview>> =
        when (reviewsListResponse) {
            is Result.Success -> Result.Success(createUIReviewResponse(reviewsListResponse.data))
            else -> mapErrorOrException(reviewsListResponse)
        }

    private fun createUIReviewResponse(response: NetworkPaginated<NetworkReview>) =
        UIPaginated(
            response.page,
            response.results.map { createUIReview(it) },
            response.totalPages
        )

    private fun createUIReview(networkReview: NetworkReview) =
        UIReview(
            networkReview.author,
            networkReview.content,
            networkReview.authorDetails.avatar,
            networkReview.authorDetails.rating
        )
}
