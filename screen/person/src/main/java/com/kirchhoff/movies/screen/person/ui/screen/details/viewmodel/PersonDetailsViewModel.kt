package com.kirchhoff.movies.screen.person.ui.screen.details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.screen.person.data.UIPersonCredits
import com.kirchhoff.movies.screen.person.data.UIPersonDetails
import com.kirchhoff.movies.screen.person.repository.IPersonsRepository
import com.kirchhoff.movies.screen.person.ui.screen.details.model.PersonDetailsScreenState
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import timber.log.Timber

internal class PersonDetailsViewModel(
    private val person: UIPerson,
    private val personRepository: IPersonsRepository
) : ViewModel() {

    val screenState: MutableLiveData<PersonDetailsScreenState> = MutableLiveData()

    init {
        screenState.value = PersonDetailsScreenState(
            name = person.name,
            title = person.name,
            details = UIPersonDetails(
                birthday = "",
                placeOfBirth = "",
                biography = "",
                alsoKnownAs = emptyList()
            ),
            credits = UIPersonCredits(
                cast = emptyList(),
                crew = emptyList()
            ),
            images = emptyList(),
            isLoading = false
        )
    }

    fun loadDetails() {
        screenState.value = screenState.value?.copy(isLoading = true)
        viewModelScope.launch {
            val result = personRepository.fetchPersonDetail(person.id)

            screenState.value = screenState.value?.copy(isLoading = false)
            when (result) {
                is Result.Success -> {
                    screenState.value = screenState.value?.copy(
                        details = result.data
                    )

                    awaitAll(
                        async { fetchCredits() },
                        async { fetchImages() }
                    )
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

    private suspend fun fetchImages() {
        when (val personImages = personRepository.fetchPersonImages(person.id)) {
            is Result.Success -> screenState.value = screenState.value?.copy(
                images = personImages.data
            )
            else -> Timber.e(personImages.toString())
        }
    }
}
