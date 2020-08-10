package com.kirchhoff.movies.data.ui.details.review

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class UIReviewsListResponse(
    val page: Int,
    val results: List<UIReview>,
    val totalPages: Int
)

@Parcelize
data class UIReview(
    val id: String,
    val author: String,
    val content: String
) : Parcelable
