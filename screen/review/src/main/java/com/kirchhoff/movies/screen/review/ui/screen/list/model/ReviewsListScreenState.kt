package com.kirchhoff.movies.screen.review.ui.screen.list.model

import com.kirchhoff.movies.screen.review.data.UIReview

internal data class ReviewsListScreenState(
    val title: String,
    val reviewsList: List<UIReview>,
    val errorMessage: String,
    val loadingVisible: Boolean,
    val paginationVisible: Boolean,
    val reviewsVisible: Boolean,
    val emptyTextVisible: Boolean
)
