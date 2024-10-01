package com.kirchhoff.movies.screen.movie.ui.screen.images.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.screen.movie.ui.screen.images.model.MovieImagesScreenState
import com.kirchhoff.movies.screen.movie.ui.screen.images.usecase.IMovieImagesUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

internal class MovieImagesViewModel(
    private val movie: UIMovie,
    private val movieImagesUseCase: IMovieImagesUseCase
) : ViewModel() {

    val screenState: MutableLiveData<MovieImagesScreenState> = MutableLiveData()

    init {
        screenState.value = MovieImagesScreenState.Default
    }

    fun loadImages() {
        viewModelScope.launch {
            movieImagesUseCase.fetchImages(movie.id).fold(
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
