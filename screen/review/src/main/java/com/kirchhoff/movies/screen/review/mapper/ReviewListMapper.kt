package com.kirchhoff.movies.screen.review.mapper

import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
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

    private fun NetworkReview.toUIReview(): ReviewUIInfo {
        val name = authorDetails.name?.trim()
        val username = authorDetails.username?.trim()
        val displayName = when {
            !name.isNullOrBlank() -> name
            !username.isNullOrBlank() -> username
            else -> null
        }
        val displayUsername = if (
            !name.isNullOrBlank() &&
            !username.isNullOrBlank() &&
            !name.equals(username, ignoreCase = true)
        ) {
            username
        } else {
            null
        }

        return ReviewUIInfo(
            author = ReviewUIInfo.Author(
                label = author,
                avatar = authorDetails.avatar.toAvatarUrl(),
                rating = authorDetails.rating,
                displayName = displayName,
                username = displayUsername
            ),
            content = content,
            url = url
        )
    }

    private fun String?.toAvatarUrl(): String? {
        val path = this
        if (path.isNullOrBlank()) {
            return null
        }

        return when {
            path.startsWith("/https://") || path.startsWith("/http://") -> path.drop(1)
            path.startsWith("https://") || path.startsWith("http://") -> path
            else -> BASE_POSTER_PATH + path
        }
    }
}
