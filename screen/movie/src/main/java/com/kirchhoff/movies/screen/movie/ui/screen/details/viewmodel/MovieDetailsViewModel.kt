package com.kirchhoff.movies.screen.movie.ui.screen.details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIEntertainmentCredits
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.ui.screen.details.model.MovieDetailsScreenState
import com.kirchhoff.movies.screen.movie.ui.screen.details.usecase.IMovieDetailsUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

internal class MovieDetailsViewModel(
    private val movieId: MovieId,
    private val movieDetailsUseCase: IMovieDetailsUseCase
) : ViewModel() {

    val screenState: MutableLiveData<MovieDetailsScreenState> = MutableLiveData()

    init {
        screenState.value = MovieDetailsScreenState.Default
    }

    fun loadDetails() {
        viewModelScope.launch {
            movieDetailsUseCase.fetchMovie(movieId).fold(
                onSuccess = { movie ->
                    screenState.value = screenState.value?.copy(
                        title = StringValue.SimpleText(movie.title),
                        backdropPath = movie.backdropPath,
                        posterPath = movie.posterPath,
                        similarMoviesTitle = StringValue.IdText(R.string.movie_similar_movies)
                    )
                },
                onFailure = { exception ->
                    screenState.value = screenState.value?.copy(errorMessage = StringValue.SimpleText(exception.localizedMessage.orEmpty()))
                    return@launch
                }
            )

            screenState.value = screenState.value?.copy(isLoading = true)
            val result = movieDetailsUseCase.fetchDetails(movieId)
            screenState.value = screenState.value?.copy(isLoading = false)

            result.fold(
                onSuccess = { data ->
                    screenState.value = screenState.value?.copy(info = data)
                    awaitAll(
                        async { fetchTrailers() },
                        async { fetchMovieCredits() },
                        async { fetchSimilarMovies() },
                        async { fetchImages() }
                    )
                },
                onFailure = { exception ->
                    screenState.value = screenState.value?.copy(errorMessage = StringValue.SimpleText(exception.localizedMessage.orEmpty()))
                }
            )
        }
    }

    private suspend fun fetchTrailers() {
        movieDetailsUseCase.fetchTrailersList(movieId).onSuccess {
            screenState.value = screenState.value?.copy(trailers = it)
        }
    }

    private suspend fun fetchMovieCredits() {
        movieDetailsUseCase.fetchMovieCredits(movieId).onSuccess {
            val castCredits = it.cast?.take(DISPLAYING_DATA_AMOUNT) ?: emptyList()
            val crewCredits = it.crew?.take(DISPLAYING_DATA_AMOUNT) ?: emptyList()
            screenState.value = screenState.value?.copy(credits = UIEntertainmentCredits(castCredits, crewCredits))
        }
    }

    private suspend fun fetchSimilarMovies() {
        movieDetailsUseCase.fetchSimilarMovies(id = movieId, 1).onSuccess { similarMovies ->
            val resultSimilarMovies = if (similarMovies.results.isNotEmpty()) {
                similarMovies.results.take(DISPLAYING_DATA_AMOUNT)
            } else {
                emptyList()
            }

            screenState.value = screenState.value?.copy(similarMovies = resultSimilarMovies)
        }
    }

    private suspend fun fetchImages() {
        movieDetailsUseCase.fetchImages(movieId).onSuccess { images ->
            val resultImages = if (images.isNotEmpty()) {
                images.take(DISPLAYING_DATA_AMOUNT)
            } else {
                emptyList()
            }

            screenState.value = screenState.value?.copy(images = resultImages)
        }
    }

    private companion object {
        const val DISPLAYING_DATA_AMOUNT = 10
    }
}
