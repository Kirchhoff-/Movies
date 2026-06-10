package com.kirchhoff.movies.screen.review.mapper

import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.review.NetworkReview
import com.kirchhoff.movies.screen.review.data.ReviewUIInfo

internal class ReviewListMapper : BaseMapper() {

    fun createUIReviewList(reviewsListResponse: NetworkPaginated<NetworkReview>): UIPaginated<ReviewUIInfo> = UIPaginated(
        page = reviewsListResponse.page,
        results = reviewsListResponse.results.map { it.toUIReview() },
        totalPages = reviewsListResponse.totalPages
    )

    private fun NetworkReview.toUIReview(): ReviewUIInfo = ReviewUIInfo(
        author = author,
        content = content,
        authorAvatar = authorDetails.avatar,
        rating = authorDetails.rating,
        url = url
    )
}
