package com.kirchhoff.movies.screen.tvshow.ui.screen.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.screen.tvshow.data.UITvShowInfo
import com.kirchhoff.movies.screen.tvshow.repository.ITvShowRepository
import kotlinx.coroutines.launch
import timber.log.Timber

internal class TvShowDetailsViewModel(private val tvRepository: ITvShowRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _exception = MutableLiveData<String>()
    val exception: LiveData<String> = _exception

    private val _tvDetails = MutableLiveData<UITvShowInfo>()
    val tvDetails: LiveData<UITvShowInfo> = _tvDetails

    private val _tvCredits = MutableLiveData<UIEntertainmentCredits>()
    val tvCredits: LiveData<UIEntertainmentCredits> = _tvCredits

    fun loadTvDetails(tvId: Int) {
        _loading.postValue(true)

        viewModelScope.launch {
            val result = tvRepository.fetchDetails(tvId)

            _loading.postValue(false)
            when (result) {
                is Result.Success -> _tvDetails.postValue(result.data)
                is Result.Error -> _error.postValue(result.toString())
                is Result.Exception -> _exception.postValue(result.toString())
            }

            if (result is Result.Success) {
                when (val credits = tvRepository.fetchCredits(tvId)) {
                    is Result.Success -> _tvCredits.postValue(credits.data)
                    else -> Timber.e(credits.toString())
                }
            }
        }
    }
}
