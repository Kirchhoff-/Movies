package com.kirchhoff.movies.screen.person.ui.screen.details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.screen.person.ui.screen.details.model.PersonDetailsScreenState
import com.kirchhoff.movies.screen.person.ui.screen.details.usecase.IPersonDetailsUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import timber.log.Timber

internal class PersonDetailsViewModel(
    private val person: UIPerson,
    private val personDetailsUseCase: IPersonDetailsUseCase
) : ViewModel() {

    val screenState: MutableLiveData<PersonDetailsScreenState> = MutableLiveData()

    init {
        screenState.value = PersonDetailsScreenState.Default
    }

    fun loadDetails() {
        screenState.value = screenState.value?.copy(isLoading = true)
        viewModelScope.launch {
            val result = personDetailsUseCase.fetchDetails(person.id)

            screenState.value = screenState.value?.copy(isLoading = false)
            when (result) {
                is Result.Success -> {
                    screenState.value = screenState.value?.copy(details = result.data)

                    awaitAll(
                        async { fetchCredits() },
                        async { fetchImages() }
                    )
                }
                else -> screenState.value = screenState.value?.copy(errorMessage = result.toString())
            }
        }
    }

    private suspend fun fetchCredits() {
        when (val creditsResult = personDetailsUseCase.fetchCredits(person.id)) {
            is Result.Success -> screenState.value = screenState.value?.copy(credits = creditsResult.data)
            else -> Timber.e(creditsResult.toString())
        }
    }

    private suspend fun fetchImages() {
        when (val personImages = personDetailsUseCase.fetchImages(person.id)) {
            is Result.Success -> screenState.value = screenState.value?.copy(images = personImages.data)
            else -> Timber.e(personImages.toString())
        }
    }
}
