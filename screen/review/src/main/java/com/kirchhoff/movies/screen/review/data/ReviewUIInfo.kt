package com.kirchhoff.movies.screen.review.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ReviewUIInfo(
    val author: Author,
    val content: String,
    val url: String
) : Parcelable {

    @Parcelize
    data class Author(
        val label: String,
        val avatar: String?,
        val rating: Int?,
        val displayName: String?,
        val username: String?
    ) : Parcelable {
        companion object {
            val Default = Author(
                label = "",
                avatar = "",
                rating = 0,
                displayName = null,
                username = null
            )
        }
    }

    companion object {
        val Default = ReviewUIInfo(
            author = Author.Default,
            content = "",
            url = ""
        )
    }
}
