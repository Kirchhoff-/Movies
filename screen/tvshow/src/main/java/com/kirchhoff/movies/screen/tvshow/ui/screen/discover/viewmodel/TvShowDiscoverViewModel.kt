package com.kirchhoff.movies.screen.tvshow.ui.screen.discover.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.model.TvShowDiscoverScreenState
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.usecase.ITvShowDiscoverUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class TvShowDiscoverViewModel(private val tvShowDiscoverUseCase: ITvShowDiscoverUseCase) : ViewModel() {

    val screenState: MutableStateFlow<TvShowDiscoverScreenState> = MutableStateFlow(TvShowDiscoverScreenState.Default)

    fun discover() {
        viewModelScope.launch {
            val result = tvShowDiscoverUseCase.discoverTvShows()
            screenState.update {
                it.copy(
                    airingToday = result.airingToday,
                    onTheAir = result.onTheAir,
                    popular = result.popular,
                    topRated = result.topRated,
                    isLoading = false
                )
            }
        }
    }
}
