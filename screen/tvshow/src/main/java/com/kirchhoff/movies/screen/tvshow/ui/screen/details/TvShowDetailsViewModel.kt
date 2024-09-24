package com.kirchhoff.movies.screen.tvshow.ui.screen.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UITv
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
        screenState.value = TvShowDetailsScreenState.Default
    }

    fun loadDetails() {
        screenState.value = screenState.value?.copy(isLoading = true)
        viewModelScope.launch {
            val result = tvShowDetailsUseCase.fetchDetails(tvShow.id)

            screenState.value = screenState.value?.copy(isLoading = false)
            result.fold(
                onSuccess = { data ->
                    screenState.value = screenState.value?.copy(
                        overview = data.overview,
                        genres = data.genres,
                        detailsInfo = TvShowDetailsInfo(
                            posterPath = tvShow.posterPath,
                            numberOfSeasons = data.numberOfSeasons,
                            numberOfEpisodes = data.numberOfEpisodes,
                            status = data.status,
                            firstAirDate = data.firstAirDate,
                            voteCount = data.voteCount,
                            voteAverage = data.voteAverage
                        )
                    )
                    awaitAll(
                        async { fetchCredits() },
                        async { fetchSimilarTvShows() }
                    )
                },
                onFailure = { screenState.value = screenState.value?.copy(errorMessage = StringValue.SimpleText(result.toString())) }
            )
        }
    }

    private suspend fun fetchCredits() {
        tvShowDetailsUseCase.fetchCredits(tvShow.id).fold(
            onSuccess = { screenState.value = screenState.value?.copy(credits = it) },
            onFailure = { Timber.e(it.localizedMessage) }
        )
    }

    private suspend fun fetchSimilarTvShows() {
        var resultSimilarTvShows: List<UITv> = emptyList()
        tvShowDetailsUseCase.fetchSimilar(
            id = tvShow.id,
            page = 1
        ).onSuccess {
            if (it.results.isNotEmpty()) resultSimilarTvShows = it.results.take(DISPLAYING_DATA_AMOUNT)
        }

        screenState.value = screenState.value?.copy(similarTvShows = resultSimilarTvShows)
    }

    private companion object {
        const val DISPLAYING_DATA_AMOUNT = 10
    }
}
