package com.kirchhoff.movies.ui.screens.main.persons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.data.PersonsResponse
import com.kirchhoff.movies.repository.Result
import com.kirchhoff.movies.repository.person.IPersonsRepository
import kotlinx.coroutines.launch

class PersonsVM(private val personRepository: IPersonsRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _paginating = MutableLiveData<Boolean>()
    val paginating: LiveData<Boolean> = _paginating

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _personsResponse = MutableLiveData<PersonsResponse>()
    val personsResponse: LiveData<PersonsResponse> = _personsResponse

    fun loadPersons(page: Int) {
        if (page == 1) {
            _loading.postValue(true)
        } else {
            _paginating.postValue(true)
        }

        viewModelScope.launch {
            val result = personRepository.fetchPopularPersons(page)

            if (page == 1) {
                _loading.postValue(false)
            } else {
                _paginating.postValue(false)
            }

            when (result) {
                is Result.Success -> _personsResponse.postValue(result.data)
                else -> _error.postValue(result.toString())
            }
        }
    }
}
