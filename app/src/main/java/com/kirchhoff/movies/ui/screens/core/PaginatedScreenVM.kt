package com.kirchhoff.movies.ui.screens.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.data.responses.PaginatedResponse
import com.kirchhoff.movies.repository.Result
import kotlinx.coroutines.launch

abstract class PaginatedScreenVM<T : PaginatedResponse<*>> : ViewModel() {

    protected abstract suspend fun loadData(page: Int, dataId: Int = 0): Result<T>

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _paginating = MutableLiveData<Boolean>()
    val paginating: LiveData<Boolean> = _paginating

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _data = MutableLiveData<T>()
    val data: LiveData<T> = _data

    fun fetchData(page: Int, dataId: Int = 0) {
        if (page == 1) {
            _loading.postValue(true)
        } else {
            _paginating.postValue(true)
        }

        viewModelScope.launch {
            val result = loadData(page, dataId)

            if (page == 1) {
                _loading.postValue(false)
            } else {
                _paginating.postValue(false)
            }

            when (result) {
                is Result.Success -> _data.postValue(result.data)
                else -> _error.postValue(result.toString())
            }
        }
    }
}