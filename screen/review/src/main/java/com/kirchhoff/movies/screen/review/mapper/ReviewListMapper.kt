package com.kirchhoff.movies.screen.review.mapper

import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.review.NetworkReview
import com.kirchhoff.movies.screen.review.data.ReviewUIInfo
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

internal class ReviewListMapper : BaseMapper() {

    fun createUIReviewList(reviewsListResponse: NetworkPaginated<NetworkReview>): UIPaginated<ReviewUIInfo> = UIPaginated(
        page = reviewsListResponse.page,
        results = reviewsListResponse.results.mapNotNull { it.toUIReview() },
        totalPages = reviewsListResponse.totalPages
    )

    private fun NetworkReview.toUIReview(): ReviewUIInfo? {
        val publicationDate = parsePublicationDate(createdAt) ?: return null
        val name = authorDetails.name?.trim()
        val username = authorDetails.username?.trim()

        return ReviewUIInfo(
            author = ReviewUIInfo.Author(
                label = author,
                avatar = authorDetails.avatar.toAvatarUrl(),
                rating = authorDetails.rating,
                displayName = if (name.isNullOrBlank()) null else name,
                username = if (username.isNullOrBlank()) null else username,
                profileUrl = username.toProfileUrl()
            ),
            content = content,
            url = url,
            publicationDate = formatPublicationDate(publicationDate)
        )
    }

    private fun parsePublicationDate(createdAt: String): Date? {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ROOT)
        parser.isLenient = false
        parser.timeZone = TimeZone.getTimeZone("UTC")

        return try {
            val date = parser.parse(createdAt)
            if (date != null && parser.format(date) == createdAt) date else null
        } catch (_: ParseException) {
            null
        }
    }

    private fun formatPublicationDate(date: Date): String {
        val formatter = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("UTC")

        return formatter.format(date)
    }

    private fun String?.toProfileUrl(): String? {
        if (isNullOrBlank()) {
            return null
        }

        return "https://www.themoviedb.org/u/$this"
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
