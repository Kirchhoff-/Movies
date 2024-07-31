package com.kirchhoff.movies.screen.tvshow.ui.screen.discover.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.model.TvShowDiscoverScreenState
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.usecase.ITvShowDiscoverUseCase
import kotlinx.coroutines.launch

internal class TvShowDiscoverViewModel(private val tvShowDiscoverUseCase: ITvShowDiscoverUseCase) : ViewModel() {

    val screenState: MutableLiveData<TvShowDiscoverScreenState> = MutableLiveData()

    init {
        screenState.value = TvShowDiscoverScreenState.Default
    }

    fun discover() {
        screenState.value = screenState.value?.copy(isLoading = true)
        viewModelScope.launch {
            val result = tvShowDiscoverUseCase.discoverTvShows()
            screenState.value = screenState.value?.copy(
                airingToday = result.airingToday,
                onTheAir = result.onTheAir,
                popular = result.popular,
                topRated = result.topRated,
                isLoading = false
            )
        }
    }
}
