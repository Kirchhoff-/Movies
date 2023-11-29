package com.kirchhoff.movies.screen.person.ui.screen.details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.screen.person.data.UIPersonDetails
import com.kirchhoff.movies.screen.person.repository.IPersonsRepository
import com.kirchhoff.movies.screen.person.ui.screen.details.model.PersonDetailsScreenState
import kotlinx.coroutines.launch
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.screen.person.data.UIPersonCredits
import timber.log.Timber

internal class PersonDetailsViewModel(
    private val person: UIPerson,
    private val personRepository: IPersonsRepository
) : ViewModel() {

    val screenState: MutableLiveData<PersonDetailsScreenState> = MutableLiveData()

    init {
        screenState.value = PersonDetailsScreenState(
            name = person.name,
            details = UIPersonDetails(
                birthday = "",
                placeOfBirth = "",
                biography = "",
                alsoKnownAs = emptyList()
            ),
            credits = UIPersonCredits(
                cast = emptyList(),
                crew = emptyList()
            )
        )
    }

    fun loadDetails() {
        //Show loading
        viewModelScope.launch {
            val result = personRepository.fetchPersonDetail(person.id)

            //Hide loading
            when (result) {
                is Result.Success -> {
                    screenState.value = screenState.value?.copy(
                        details = result.data
                    )

                  fetchCredits()
                }
                else -> Timber.e("Exception = $result")
            }
        }
    }

    private suspend fun fetchCredits() {
        when (val creditsResult = personRepository.fetchPersonCredits(person.id)) {
            is Result.Success -> screenState.value = screenState.value?.copy(
                credits = creditsResult.data
            )
            else -> Timber.e(creditsResult.toString())
        }
    }
}
