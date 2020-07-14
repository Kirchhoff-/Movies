package com.kirchhoff.movies.ui.screens.details.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.data.responses.MovieDetails
import com.kirchhoff.movies.repository.Result
import com.kirchhoff.movies.repository.movie.IMovieRepository
import kotlinx.coroutines.launch

class MovieDetailsVM(private val movieRepository: IMovieRepository): ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    fun loadMovieDetails(movieId: Int) {
        _loading.postValue(true)

        viewModelScope.launch {
            when (val result = movieRepository.fetchDetails(movieId)) {
                is Result.Success -> {
                    _movieDetails.postValue(result.data)
                }
                else -> {
                    _error.postValue(result.toString())
                }
            }

            _loading.postValue(false)
        }
    }
}