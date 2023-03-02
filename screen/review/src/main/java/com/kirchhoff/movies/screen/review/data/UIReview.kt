package com.kirchhoff.movies.screen.review.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIReview(
    val author: String,
    val content: String,
    val authorAvatar: String?,
    val rating: Int?
) : Parcelable
