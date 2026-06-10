package com.kirchhoff.movies.screen.review.ui.screen.list.model

import com.kirchhoff.movies.screen.review.data.ReviewUIInfo

internal data class ReviewsListScreenState(
    val title: String,
    val reviewsList: List<ReviewUIInfo>,
    val errorMessage: String,
    val loadingVisible: Boolean,
    val paginationVisible: Boolean,
    val reviewsVisible: Boolean,
    val emptyTextVisible: Boolean
) {
    companion object {
        val Default = ReviewsListScreenState(
            title = "",
            reviewsList = emptyList(),
            errorMessage = "",
            loadingVisible = false,
            paginationVisible = false,
            reviewsVisible = false,
            emptyTextVisible = false
        )
    }
}
