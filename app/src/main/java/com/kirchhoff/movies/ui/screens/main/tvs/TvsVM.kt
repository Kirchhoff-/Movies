package com.kirchhoff.movies.ui.screens.main.tvs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.data.DiscoverTvsResponse
import com.kirchhoff.movies.repository.Result
import com.kirchhoff.movies.repository.discover.IDiscoverRepository
import kotlinx.coroutines.launch

class TvsVM(private val discoverRepository: IDiscoverRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _paginating = MutableLiveData<Boolean>()
    val paginating: LiveData<Boolean> = _paginating

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _tvsResponse = MutableLiveData<DiscoverTvsResponse>()
    val tvsResponse: LiveData<DiscoverTvsResponse> = _tvsResponse

    fun discoverTvs(page: Int) {
        if (page == 1) {
            _loading.postValue(true)
        } else {
            _paginating.postValue(true)
        }

        viewModelScope.launch {
            val result = discoverRepository.fetchTvs(page)

            if (page == 1) {
                _loading.postValue(false)
            } else {
                _paginating.postValue(false)
            }

            when (result) {
                is Result.Success -> _tvsResponse.postValue(result.data)
                else -> _error.postValue(result.toString())
            }
        }
    }
}
