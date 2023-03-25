package com.kirchhoff.movies.screen.review.ui.screen.list.model

import android.os.Parcelable
import com.kirchhoff.movies.screen.review.ui.screen.ReviewType
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewsListArgs(
    val id: Int,
    val title: String,
    val reviewType: ReviewType
) : Parcelable
