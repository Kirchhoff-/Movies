package com.kirchhoff.movies.screen.tvshow.ui.screen.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.screen.tvshow.data.UITvShowDetails
import com.kirchhoff.movies.screen.tvshow.repository.ITvShowRepository
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.model.TvShowDetailsScreenState
import kotlinx.coroutines.launch
import timber.log.Timber

internal class NewTvShowDetailsViewModel(
    private val tvShow: UITv,
    private val tvRepository: ITvShowRepository
) : ViewModel() {

    val screenState: MutableLiveData<TvShowDetailsScreenState> = MutableLiveData()

    init {
        screenState.value = TvShowDetailsScreenState(
            title = "",
            details = UITvShowDetails(
                numberOfEpisodes = 0,
                numberOfSeasons = 0,
                overview = "",
                status = "",
                firstAirDate = "",
                voteCount = null,
                voteAverage = null,
                genres = emptyList()
            ),
            credits = UIEntertainmentCredits(
                cast = null,
                crew = null
            ),
            isLoading = false,
            errorMessage = ""
        )
    }

    fun loadDetails() {
        screenState.value = screenState.value?.copy(isLoading = true)
        viewModelScope.launch {
            val result = tvRepository.fetchDetails(tvShow.id)

            screenState.value = screenState.value?.copy(isLoading = false)
            when (result) {
                is Result.Success -> {
                    screenState.value = screenState.value?.copy(
                        details = result.data
                    )

                    fetchCredits()
                }
                else -> {
                    screenState.value = screenState.value?.copy(
                        errorMessage = result.toString()
                    )
                }
            }
        }
    }

    private suspend fun fetchCredits() {
        when (val creditsResult = tvRepository.fetchCredits(tvShow.id)) {
            is Result.Success -> screenState.value = screenState.value?.copy(
                credits = creditsResult.data
            )
            else -> Timber.e((creditsResult.toString()))
        }
    }
}
