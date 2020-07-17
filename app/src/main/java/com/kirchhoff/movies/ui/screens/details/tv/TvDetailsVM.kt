package com.kirchhoff.movies.ui.screens.details.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.data.responses.TvDetails
import com.kirchhoff.movies.repository.Result
import com.kirchhoff.movies.repository.tv.ITvRepository
import kotlinx.coroutines.launch

class TvDetailsVM(private val tvRepository: ITvRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _exception = MutableLiveData<String>()
    val exception: LiveData<String> = _exception

    private val _tvDetails = MutableLiveData<TvDetails>()
    val tvDetails: LiveData<TvDetails> = _tvDetails

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
        }
    }
}
