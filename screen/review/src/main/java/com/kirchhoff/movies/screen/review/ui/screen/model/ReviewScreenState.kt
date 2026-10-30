package com.kirchhoff.movies.screen.review.ui.screen.model

import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.review.data.ReviewUIInfo

internal data class ReviewScreenState(
    val title: StringValue,
    val contentState: ContentState,
    val reviewsList: List<ReviewUIInfo>,
    val errorMessage: String,
    val loadingVisible: Boolean,
    val paginationVisible: Boolean,
    val reviewsVisible: Boolean,
    val emptyTextVisible: Boolean
) {
    sealed interface ContentState {
        data object List : ContentState

        data class Details(val review: ReviewUIInfo) : ContentState
    }

    companion object {
        val Default = ReviewScreenState(
            title = StringValue.Empty,
            contentState = ContentState.List,
            reviewsList = emptyList(),
            errorMessage = "",
            loadingVisible = false,
            paginationVisible = false,
            reviewsVisible = false,
            emptyTextVisible = false
        )
    }
}
