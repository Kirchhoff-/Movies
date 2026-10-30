package com.kirchhoff.movies.screen.review.ui.screen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.review.R
import com.kirchhoff.movies.screen.review.data.ReviewUIInfo
import com.kirchhoff.movies.screen.review.ui.screen.ReviewType
import com.kirchhoff.movies.screen.review.ui.screen.list.model.ReviewsListArgs
import com.kirchhoff.movies.screen.review.ui.screen.model.ReviewScreenState
import com.kirchhoff.movies.screen.review.usecase.ReviewUseCase
import kotlinx.coroutines.launch

internal class ReviewViewModel(
    private val args: ReviewsListArgs,
    private val useCase: ReviewUseCase
) : ViewModel() {

    val screenState: MutableLiveData<ReviewScreenState> = MutableLiveData()

    private val reviewTitle: String

    private var currentPage: Int = 0
    private var totalPages: Int = Int.MAX_VALUE
    private var isLoading: Boolean = false

    init {
        reviewTitle = if (args.reviewType == ReviewType.MOVIE) {
            useCase.movieTitle(args.id)
        } else {
            useCase.tvShowTitle(args.id)
        }
        screenState.value = ReviewScreenState.Default.copy(title = StringValue.IdText(R.string.review_list_title_format, reviewTitle))
    }

    fun onReviewClick(review: ReviewUIInfo) {
        screenState.value = screenState.value?.copy(
            title = StringValue.IdText(R.string.review_details_title_format, reviewTitle),
            contentState = ReviewScreenState.ContentState.Details(review)
        )
    }

    fun onCloseDetails() {
        screenState.value = screenState.value?.copy(
            title = StringValue.IdText(R.string.review_list_title_format, reviewTitle),
            contentState = ReviewScreenState.ContentState.List
        )
    }

    fun loadReviews() {
        viewModelScope.launch {
            if (currentPage < totalPages && !isLoading && screenState.value?.errorMessage?.isEmpty() == true) {
                isLoading = true

                val loadingVisible = currentPage == 0
                val paginationVisible = !loadingVisible
                screenState.value = screenState.value?.copy(
                    loadingVisible = loadingVisible,
                    paginationVisible = paginationVisible
                )

                val reviewsResult = when (args.reviewType) {
                    ReviewType.MOVIE -> useCase.fetchMovieReviews(args.id, currentPage + 1)
                    ReviewType.TV -> useCase.fetchTvReviews(args.id, currentPage + 1)
                }
                reviewsResult.fold(
                    onSuccess = { result ->
                        totalPages = result.totalPages
                        currentPage = result.page

                        val resultReviewsList = mutableListOf<ReviewUIInfo>().apply {
                            screenState.value?.let { this.addAll(it.reviewsList) }
                            addAll(result.results)
                        }

                        screenState.value = screenState.value?.copy(
                            reviewsList = resultReviewsList,
                            loadingVisible = false,
                            paginationVisible = false,
                            reviewsVisible = resultReviewsList.isNotEmpty(),
                            emptyTextVisible = resultReviewsList.isEmpty()
                        )
                    },
                    onFailure = {
                        screenState.value?.copy(
                            errorMessage = reviewsResult.toString(),
                            loadingVisible = false,
                            reviewsVisible = screenState.value?.reviewsList?.isNotEmpty() == true,
                            paginationVisible = false,
                            emptyTextVisible = false
                        )
                    }
                )
                isLoading = false
            }
        }
    }
}
