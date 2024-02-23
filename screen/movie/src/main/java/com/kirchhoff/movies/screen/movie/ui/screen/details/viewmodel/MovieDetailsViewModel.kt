package com.kirchhoff.movies.screen.movie.ui.screen.details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.movie.data.UIMovieInfo
import com.kirchhoff.movies.screen.movie.data.UITrailersList
import com.kirchhoff.movies.screen.movie.ui.screen.details.model.MovieDetailsScreenState
import com.kirchhoff.movies.screen.movie.ui.screen.details.usecase.IMovieDetailsUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

internal class MovieDetailsViewModel(
    private val movie: UIMovie,
    private val movieDetailsUseCase: IMovieDetailsUseCase
) : ViewModel() {

    val screenState: MutableLiveData<MovieDetailsScreenState> = MutableLiveData()

    init {
        screenState.value = MovieDetailsScreenState(
            title = StringValue.SimpleText(movie.title),
            backdropPath = movie.backdropPath,
            posterPath = movie.posterPath,
            info = UIMovieInfo(
                productionCountries = emptyList(),
                productionCompanies = emptyList(),
                runtime = 0,
                tagLine = "",
                overview = "",
                releaseDate = "",
                voteCount = 0,
                voteAverage = 0f,
                genres = emptyList()
            ),
            trailers = UITrailersList(
                results = emptyList()
            ),
            credits = UIEntertainmentCredits(
                cast = emptyList(),
                crew = emptyList()
            ),
            similarMovies = emptyList(),
            images = emptyList(),
            isLoading = false,
            errorMessage = StringValue.Empty
        )
    }

    fun loadDetails() {
        screenState.value = screenState.value?.copy(isLoading = true)
        viewModelScope.launch {
            val result = movieDetailsUseCase.fetchDetails(movie.id)

            screenState.value = screenState.value?.copy(isLoading = false)
            when (result) {
                is Result.Success -> {
                    screenState.value = screenState.value?.copy(info = result.data)
                    awaitAll(
                        async { fetchTrailers() },
                        async { fetchMovieCredits() },
                        async { fetchSimilarMovies() },
                        async { fetchImages() }
                    )
                }

                else -> {
                    screenState.value = screenState.value?.copy(errorMessage = StringValue.SimpleText(result.toString()))
                }
            }
        }
    }

    private suspend fun fetchTrailers() {
        when (val trailersResult = movieDetailsUseCase.fetchTrailersList(movie.id)) {
            is Result.Success -> screenState.value = screenState.value?.copy(trailers = trailersResult.data)
            else -> {}
        }
    }

    private suspend fun fetchMovieCredits() {
        when (val creditsResult = movieDetailsUseCase.fetchMovieCredits(movie.id)) {
            is Result.Success -> screenState.value = screenState.value?.copy(credits = creditsResult.data)
            else -> {}
        }
    }

    private suspend fun fetchSimilarMovies() {
        val similarMoviesResult = movieDetailsUseCase.fetchSimilarMovies(id = movie.id, 1)
        val resultSimilarMoviesList = if (
            similarMoviesResult is Result.Success &&
            similarMoviesResult.data.results.isNotEmpty()
        ) {
            similarMoviesResult.data.results.take(DISPLAYING_DATA_AMOUNT)
        } else {
            emptyList()
        }

        screenState.value = screenState.value?.copy(similarMovies = resultSimilarMoviesList)
    }

    private suspend fun fetchImages() {
        val imagesResult = movieDetailsUseCase.fetchImages(movie.id)
        val resultImages = if (
            imagesResult is Result.Success &&
            imagesResult.data.isNotEmpty()
        ) {
            imagesResult.data.take(DISPLAYING_DATA_AMOUNT)
        } else {
            emptyList()
        }
        screenState.value = screenState.value?.copy(images = resultImages)
    }

    private companion object {
        const val DISPLAYING_DATA_AMOUNT = 10
    }
}
