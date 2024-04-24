package com.kirchhoff.movies.screen.review.mapper

import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.review.NetworkReview
import com.kirchhoff.movies.screen.review.data.UIReview

internal interface IReviewListMapper {
    fun createUIReviewList(reviewsListResponse: Result<NetworkPaginated<NetworkReview>>): Result<UIPaginated<UIReview>>
}

internal class ReviewListMapper : BaseMapper(), IReviewListMapper {

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
