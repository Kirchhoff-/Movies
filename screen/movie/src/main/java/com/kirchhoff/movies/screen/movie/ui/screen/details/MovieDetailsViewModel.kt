package com.kirchhoff.movies.screen.movie.ui.screen.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.screen.movie.data.UIMovieDetails
import com.kirchhoff.movies.screen.movie.data.UITrailer
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val movieRepository: IMovieRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _exception = MutableLiveData<String>()
    val exception: LiveData<String> = _exception

    private val _movieDetails = MutableLiveData<UIMovieDetails>()
    val movieDetails: LiveData<UIMovieDetails> = _movieDetails

    private val _trailers = MutableLiveData<List<UITrailer>>()
    val trailers: LiveData<List<UITrailer>> = _trailers

    private val _movieCredits = MutableLiveData<UIEntertainmentCredits>()
    val movieCredits: LiveData<UIEntertainmentCredits> = _movieCredits

    private val _similarMovies = MutableLiveData<List<UIMovie>>()
    val similarMovies: LiveData<List<UIMovie>> = _similarMovies

    fun loadMovieDetails(movieId: Int) {
        _loading.postValue(true)

        viewModelScope.launch {
            val result = movieRepository.fetchDetails(movieId)

            _loading.postValue(false)
            when (result) {
                is Result.Success -> _movieDetails.postValue(result.data)
                is Result.Error -> _error.postValue(result.toString())
                is Result.Exception -> _exception.postValue(result.toString())
            }

            if (result is Result.Success) {
                when (val trailersResult = movieRepository.fetchTrailersList(movieId)) {
                    is Result.Success -> _trailers.postValue(trailersResult.data.results)
                    else -> {}
                }
            }

            if (result is Result.Success) {
                when (val creditsResult = movieRepository.fetchMovieCredits(movieId)) {
                    is Result.Success -> _movieCredits.postValue(creditsResult.data)
                    else -> {}
                }
            }

            val similarMoviesResult = movieRepository.fetchSimilarMovies(movieId = movieId, 1)
            val resultSimilarMoviesList = if (
                similarMoviesResult is Result.Success &&
                similarMoviesResult.data.results.isNotEmpty()
            ) {
                similarMoviesResult.data.results.subList(0, SIMILAR_MOVIES_AMOUNT)
            } else emptyList()
            _similarMovies.postValue(resultSimilarMoviesList)
        }
    }

    private companion object {
        const val SIMILAR_MOVIES_AMOUNT = 10
    }
}
