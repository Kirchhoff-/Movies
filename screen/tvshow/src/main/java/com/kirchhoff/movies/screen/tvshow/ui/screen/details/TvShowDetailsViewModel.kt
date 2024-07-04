package com.kirchhoff.movies.screen.tvshow.ui.screen.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.model.TvShowDetailsInfo
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.model.TvShowDetailsScreenState
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.usecase.ITvShowDetailsUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import timber.log.Timber

internal class TvShowDetailsViewModel(
    private val tvShow: UITv,
    private val tvShowDetailsUseCase: ITvShowDetailsUseCase
) : ViewModel() {

    val screenState: MutableLiveData<TvShowDetailsScreenState> = MutableLiveData()

    init {
        screenState.value = TvShowDetailsScreenState(
            title = StringValue.SimpleText(tvShow.name),
            backdropPath = tvShow.backdropPath,
            posterPath = tvShow.posterPath,
            info = TvShowDetailsInfo(
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
            similarTvShows = emptyList(),
            isLoading = false,
            errorMessage = StringValue.Empty
        )
    }

    fun loadDetails() {
        screenState.value = screenState.value?.copy(isLoading = true)
        viewModelScope.launch {
            val result = tvShowDetailsUseCase.fetchDetails(tvShow.id)

            screenState.value = screenState.value?.copy(isLoading = false)
            when (result) {
                is Result.Success -> {
                    screenState.value = screenState.value?.copy(info = result.data)
                    awaitAll(
                        async { fetchCredits() },
                        async { fetchSimilarTvShows() }
                    )
                }
                else -> {
                    screenState.value = screenState.value?.copy(
                        errorMessage = StringValue.SimpleText(result.toString())
                    )
                }
            }
        }
    }

    private suspend fun fetchCredits() {
        when (val creditsResult = tvShowDetailsUseCase.fetchCredits(tvShow.id)) {
            is Result.Success -> screenState.value = screenState.value?.copy(
                credits = creditsResult.data
            )
            else -> Timber.e((creditsResult.toString()))
        }
    }

    private suspend fun fetchSimilarTvShows() {
        val similarTvShows = tvShowDetailsUseCase.fetchSimilar(id = tvShow.id, page = 1)
        val resultSimilarTvShowsList = if (
            similarTvShows is Result.Success &&
            similarTvShows.data.results.isNotEmpty()
        ) {
            similarTvShows.data.results.take(DISPLAYING_DATA_AMOUNT)
        } else {
            emptyList()
        }

        screenState.value = screenState.value?.copy(similarTvShows = resultSimilarTvShowsList)
    }

    private companion object {
        const val DISPLAYING_DATA_AMOUNT = 10
    }
}
