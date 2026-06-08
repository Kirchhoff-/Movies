package com.kirchhoff.movies.screen.movie.ui.screen.images.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.screen.movie.ui.screen.images.model.MovieImagesScreenState
import com.kirchhoff.movies.screen.movie.usecase.MovieUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

internal class MovieImagesViewModel(
    private val movieId: MovieId,
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    val screenState: MutableLiveData<MovieImagesScreenState> = MutableLiveData()

    init {
        screenState.value = MovieImagesScreenState.Default
    }

    fun loadImages() {
        viewModelScope.launch {
            movieUseCase.fetchImages(movieId).fold(
                onSuccess = { data ->
                    screenState.value = screenState.value?.copy(image = data)
                },
                onFailure = { exception ->
                    Timber.e("Images error = ${exception.localizedMessage.orEmpty()}")
                }
            )
        }
    }
}
