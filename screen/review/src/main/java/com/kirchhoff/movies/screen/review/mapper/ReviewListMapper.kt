package com.kirchhoff.movies.screen.review.mapper

import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.review.NetworkReview
import com.kirchhoff.movies.screen.review.data.UIReview

internal interface IReviewListMapper {
    fun createUIReviewList(reviewsListResponse: NetworkPaginated<NetworkReview>): UIPaginated<UIReview>
}

internal class ReviewListMapper : BaseMapper(), IReviewListMapper {

    override fun createUIReviewList(reviewsListResponse: NetworkPaginated<NetworkReview>): UIPaginated<UIReview> = UIPaginated(
        page = reviewsListResponse.page,
        results = reviewsListResponse.results.map { it.toUIReview() },
        totalPages = reviewsListResponse.totalPages
    )

    private fun NetworkReview.toUIReview(): UIReview = UIReview(
        author = author,
        content = content,
        authorAvatar = authorDetails.avatar,
        rating = authorDetails.rating
    )
}
