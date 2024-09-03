package com.kirchhoff.movies.screen.movie.ui.screen.discover.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.screen.movie.ui.screen.discover.model.MovieDiscoverScreenState
import com.kirchhoff.movies.screen.movie.ui.screen.discover.usecase.IMovieDiscoverUseCase
import kotlinx.coroutines.launch

internal class MovieDiscoverViewModel(private val movieDiscoverUseCase: IMovieDiscoverUseCase) : ViewModel() {

    val screenState: MutableLiveData<MovieDiscoverScreenState> = MutableLiveData()

    init {
        screenState.value = MovieDiscoverScreenState.Default
        discover()
    }

    private fun discover() {
        screenState.value = screenState.value?.copy(isLoading = true)
        viewModelScope.launch {
            val result = movieDiscoverUseCase.discoverMovies()
            screenState.value = screenState.value?.copy(
                nowPlaying = result.nowPlaying,
                popular = result.popular,
                topRated = result.topRated,
                upcoming = result.upcoming,
                isLoading = false
            )
        }
    }
}
