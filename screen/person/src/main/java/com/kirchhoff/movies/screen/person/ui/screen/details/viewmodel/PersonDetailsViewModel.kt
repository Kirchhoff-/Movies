package com.kirchhoff.movies.screen.person.ui.screen.details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirchhoff.movies.screen.person.repository.IPersonsRepository
import com.kirchhoff.movies.screen.person.ui.screen.details.model.PersonDetailsScreenState

internal class PersonDetailsViewModel(
    private val personId: Int,
    private val personRepository: IPersonsRepository
) : ViewModel() {

    val screenState: MutableLiveData<PersonDetailsScreenState> = MutableLiveData()

    init {
        screenState.value = PersonDetailsScreenState(
            stub = ""
        )
    }

    fun loadDetails() {
        //TODO perform requests
    }
}
