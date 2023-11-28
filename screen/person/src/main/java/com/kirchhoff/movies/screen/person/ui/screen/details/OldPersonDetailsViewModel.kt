package com.kirchhoff.movies.screen.person.ui.screen.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.screen.person.data.UIPersonCredits
import com.kirchhoff.movies.screen.person.data.UIPersonDetails
import com.kirchhoff.movies.screen.person.data.UIPersonImage
import com.kirchhoff.movies.screen.person.repository.IPersonsRepository
import kotlinx.coroutines.launch
import timber.log.Timber

internal class OldPersonDetailsViewModel(private val personRepository: IPersonsRepository) : ViewModel() {

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

    private val _personImages = MutableLiveData<List<UIPersonImage>>()
    val personImages: LiveData<List<UIPersonImage>> = _personImages

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
                when (val personImages = personRepository.fetchPersonImages(personId)) {
                    is Result.Success -> _personImages.postValue(personImages.data)
                    else -> Timber.e(personImages.toString())
                }

                when (val creditsResult = personRepository.fetchPersonCredits(personId)) {
                    is Result.Success -> _personCredits.postValue(creditsResult.data)
                    else -> Timber.e(creditsResult.toString())
                }
            }
        }
    }
}
