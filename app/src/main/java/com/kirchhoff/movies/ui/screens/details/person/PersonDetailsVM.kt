package com.kirchhoff.movies.ui.screens.details.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.data.ui.details.person.UIPersonCredits
import com.kirchhoff.movies.data.ui.details.person.UIPersonDetails
import com.kirchhoff.movies.repository.Result
import com.kirchhoff.movies.repository.person.IPersonsRepository
import kotlinx.coroutines.launch

class PersonDetailsVM(private val personRepository: IPersonsRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _exception = MutableLiveData<String>()
    val exception: LiveData<String> = _exception

    private val _personDetails = MutableLiveData<UIPersonDetails>()
    val personDetails: LiveData<UIPersonDetails> = _personDetails

    private val _personCredits = MutableLiveData<UIPersonCredits>()
    val personCredits: LiveData<UIPersonCredits> = _personCredits

    fun loadPersonDetails(personId: Int) {
        _loading.postValue(true)

        viewModelScope.launch {
            val result = personRepository.fetchPersonDetail(personId)

            _loading.postValue(false)
            when (result) {
                is Result.Success -> _personDetails.postValue(result.data)
                is Result.Error -> _error.postValue(result.toString())
                is Result.Exception -> _exception.postValue(result.toString())
            }

            if (result is Result.Success) {
                when (val creditsResult = personRepository.fetchPersonCredits(personId)) {
                    is Result.Success -> _personCredits.postValue(creditsResult.data)
                }
            }
        }
    }
}
