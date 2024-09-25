package com.kirchhoff.movies.screen.person.ui.screen.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.screen.person.ui.screen.list.model.PersonListScreenState
import com.kirchhoff.movies.screen.person.ui.screen.list.usecase.IPersonListUseCase
import kotlinx.coroutines.launch

internal class PersonListViewModel(private val personListUseCase: IPersonListUseCase) : ViewModel() {

    val screenState: MutableLiveData<PersonListScreenState> = MutableLiveData()

    private var currentPage: Int = 0
    private var totalPages: Int = Int.MAX_VALUE
    private var isLoading: Boolean = false

    init {
        screenState.value = PersonListScreenState.Default
    }

    fun loadPersonList() {
        viewModelScope.launch {
            if (currentPage < totalPages && !isLoading && screenState.value?.errorMessage?.isEmpty() == true) {
                isLoading = true

                val loadingVisible = currentPage == 0
                val paginationVisible = !loadingVisible
                screenState.value = screenState.value?.copy(
                    loadingVisible = loadingVisible,
                    paginationVisible = paginationVisible
                )

                personListUseCase.fetchPopularPersons(currentPage + 1).fold(
                    onSuccess = { response ->
                        totalPages = response.totalPages
                        currentPage = response.page

                        val personList = mutableListOf<UIPerson>().apply {
                            screenState.value?.let { this.addAll(it.personList) }
                            addAll(response.results)
                        }

                        screenState.value = screenState.value?.copy(
                            personList = personList,
                            loadingVisible = false,
                            paginationVisible = false,
                            errorMessage = ""
                        )
                    },
                    onFailure = { exception ->
                        screenState.value = screenState.value?.copy(
                            loadingVisible = false,
                            paginationVisible = false,
                            errorMessage = exception.localizedMessage.orEmpty()
                        )
                    }
                )

                isLoading = false
            }
        }
    }
}
