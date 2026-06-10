package com.kirchhoff.movies.screen.review.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ReviewUIInfo(
    val author: String,
    val content: String,
    val authorAvatar: String?,
    val rating: Int?,
    val url: String
) : Parcelable {
    companion object {
        val Default = ReviewUIInfo(
            author = "",
            content = "",
            authorAvatar = "",
            rating = 0,
            url = ""
        )
    }
}
