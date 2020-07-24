package com.kirchhoff.movies.ui.screens.reviews.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.data.responses.ReviewsListResponse
import com.kirchhoff.movies.repository.Result
import com.kirchhoff.movies.repository.movie.IMovieRepository
import com.kirchhoff.movies.repository.tv.ITvRepository
import com.kirchhoff.movies.ui.screens.reviews.ReviewType
import kotlinx.coroutines.launch

class ReviewsListVM(
    private val movieRepository: IMovieRepository,
    private val tvRepository: ITvRepository
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _paginating = MutableLiveData<Boolean>()
    val paginating: LiveData<Boolean> = _paginating

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _data = MutableLiveData<ReviewsListResponse>()
    val data: LiveData<ReviewsListResponse> = _data

    fun fetchReviews(dataId: Int, reviewType: ReviewType, page: Int) {
        if (page == 1) {
            _loading.postValue(true)
        } else {
            _paginating.postValue(true)
        }

        viewModelScope.launch {
            val result = when (reviewType) {
                ReviewType.MOVIE -> movieRepository.fetchReviewsList(dataId, page)
                ReviewType.TV -> tvRepository.fetchReviewsList(dataId, page)
            }

            if (page == 1) {
                _loading.postValue(false)
            } else {
                _paginating.postValue(false)
            }

            when (result) {
                is Result.Success -> _data.postValue(result.data)
                else -> _error.postValue(result.toString())
            }
        }
    }
}
