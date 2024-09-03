package com.kirchhoff.movies.screen.review.ui.screen.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.screen.review.data.UIReview
import com.kirchhoff.movies.screen.review.ui.screen.ReviewType
import com.kirchhoff.movies.screen.review.ui.screen.list.model.ReviewsListArgs
import com.kirchhoff.movies.screen.review.ui.screen.list.model.ReviewsListScreenState
import com.kirchhoff.movies.screen.review.usecase.IReviewUseCase
import kotlinx.coroutines.launch

internal class ReviewsListViewModel(
    private val args: ReviewsListArgs,
    private val useCase: IReviewUseCase
) : ViewModel() {

    val screenState: MutableLiveData<ReviewsListScreenState> = MutableLiveData()

    private var currentPage: Int = 0
    private var totalPages: Int = Int.MAX_VALUE
    private var isLoading: Boolean = false

    init {
        screenState.value = ReviewsListScreenState.Default
    }

    fun loadReviews() {
        screenState.value = screenState.value?.copy(
            title = if (args.reviewType == ReviewType.MOVIE) {
                useCase.movieTitle(args.id)
            } else {
                useCase.tvShowTitle(args.id)
            }
        )

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
                when (reviewsResult) {
                    is Result.Success -> {
                        totalPages = reviewsResult.data.totalPages
                        currentPage = reviewsResult.data.page

                        val resultReviewsList = mutableListOf<UIReview>().apply {
                            screenState.value?.let { this.addAll(it.reviewsList) }
                            addAll(reviewsResult.data.results)
                        }

                        screenState.value = screenState.value?.copy(
                            reviewsList = resultReviewsList,
                            loadingVisible = false,
                            paginationVisible = false,
                            reviewsVisible = resultReviewsList.isNotEmpty(),
                            emptyTextVisible = resultReviewsList.isEmpty()
                        )
                    }
                    else -> screenState.value = screenState.value?.copy(
                        errorMessage = reviewsResult.toString(),
                        loadingVisible = false,
                        reviewsVisible = screenState.value?.reviewsList?.isNotEmpty() == true,
                        paginationVisible = false,
                        emptyTextVisible = false
                    )
                }

                isLoading = false
            }
        }
    }
}
