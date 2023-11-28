package com.kirchhoff.movies.screen.person.ui.screen.details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.screen.person.data.UIPersonDetails
import com.kirchhoff.movies.screen.person.repository.IPersonsRepository
import com.kirchhoff.movies.screen.person.ui.screen.details.model.PersonDetailsScreenState
import kotlinx.coroutines.launch
import com.kirchhoff.movies.core.repository.Result
import timber.log.Timber

internal class PersonDetailsViewModel(
    private val personId: Int,
    private val personRepository: IPersonsRepository
) : ViewModel() {

    val screenState: MutableLiveData<PersonDetailsScreenState> = MutableLiveData()

    init {
        screenState.value = PersonDetailsScreenState(
            details = UIPersonDetails(
                birthday = "",
                placeOfBirth = "",
                biography = "",
                alsoKnownAs = emptyList()
            )
        )
    }

    fun loadDetails() {
        //Show loading
        viewModelScope.launch {
            val result = personRepository.fetchPersonDetail(personId)

            //Hide loading
            when (result) {
                is Result.Success -> screenState.value = screenState.value?.copy(
                    details = result.data
                )
                else -> Timber.e("Exception = $result")
            }
        }
    }
}
