package com.kirchhoff.movies.screen.review.ui.screen.list.model

import android.os.Parcelable
import com.kirchhoff.movies.screen.review.ui.screen.ReviewType
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ReviewsListArgs(
    val id: Int,
    val reviewType: ReviewType
) : Parcelable
