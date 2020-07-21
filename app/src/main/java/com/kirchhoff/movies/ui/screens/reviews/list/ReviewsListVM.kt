package com.kirchhoff.movies.ui.screens.reviews.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.data.responses.ReviewsListResponse
import com.kirchhoff.movies.repository.Result
import com.kirchhoff.movies.repository.movie.IMovieRepository
import kotlinx.coroutines.launch

class ReviewsListVM(private val movieRepository: IMovieRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _paginating = MutableLiveData<Boolean>()
    val paginating: LiveData<Boolean> = _paginating

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _data = MutableLiveData<ReviewsListResponse>()
    val data: LiveData<ReviewsListResponse> = _data

    fun fetchReviews(movieId: Int, page: Int) {
        if (page == 1) {
            _loading.postValue(true)
        } else {
            _paginating.postValue(true)
        }

        viewModelScope.launch {
            val result = movieRepository.fetchReviewsList(movieId, page)

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
