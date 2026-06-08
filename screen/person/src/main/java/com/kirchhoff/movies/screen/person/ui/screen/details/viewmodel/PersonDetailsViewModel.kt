package com.kirchhoff.movies.screen.person.ui.screen.details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.screen.person.ui.screen.details.model.PersonDetailsScreenState
import com.kirchhoff.movies.screen.person.ui.screen.details.usecase.PersonDetailsUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import timber.log.Timber

internal class PersonDetailsViewModel(
    private val personId: Int,
    private val personDetailsUseCase: PersonDetailsUseCase
) : ViewModel() {

    val screenState: MutableLiveData<PersonDetailsScreenState> = MutableLiveData()

    init {
        screenState.value = PersonDetailsScreenState.Default
    }

    fun loadDetails() {
        screenState.value = screenState.value?.copy(isLoading = true)
        viewModelScope.launch {
            val result = personDetailsUseCase.fetchDetails(personId)

            screenState.value = screenState.value?.copy(isLoading = false)
            result.fold(
                onSuccess = { details ->
                    screenState.value = screenState.value?.copy(details = details)

                    awaitAll(
                        async { fetchCredits() },
                        async { fetchImages() }
                    )
                },
                onFailure = { exception ->
                    screenState.value = screenState.value?.copy(errorMessage = exception.localizedMessage.orEmpty())
                }
            )
        }
    }

    private suspend fun fetchCredits() {
        personDetailsUseCase.fetchCredits(personId).fold(
            onSuccess = { credits ->
                screenState.value = screenState.value?.copy(credits = credits)
            },
            onFailure = { exception ->
                Timber.e(exception.localizedMessage.orEmpty())
            }
        )
    }

    private suspend fun fetchImages() {
        personDetailsUseCase.fetchImages(personId).fold(
            onSuccess = { personImages ->
                screenState.value = screenState.value?.copy(images = personImages)
            },
            onFailure = { exception ->
                Timber.e(exception.localizedMessage.orEmpty())
            }
        )
    }
}
