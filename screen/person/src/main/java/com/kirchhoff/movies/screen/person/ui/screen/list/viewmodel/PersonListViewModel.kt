package com.kirchhoff.movies.screen.person.ui.screen.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.screen.person.repository.IPersonsRepository
import com.kirchhoff.movies.screen.person.ui.screen.list.model.PersonListScreenState
import kotlinx.coroutines.launch

internal class PersonListViewModel(
    private val personRepository: IPersonsRepository
) : ViewModel() {

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

                when (val result = personRepository.fetchPopularPersons(currentPage + 1)) {
                    is Result.Success -> {
                        totalPages = result.data.totalPages
                        currentPage = result.data.page

                        val personList = mutableListOf<UIPerson>().apply {
                            screenState.value?.let { this.addAll(it.personList) }
                            addAll(result.data.results)
                        }

                        screenState.value = screenState.value?.copy(
                            personList = personList,
                            loadingVisible = false,
                            paginationVisible = false,
                            errorMessage = ""
                        )
                    }
                    else -> screenState.value = screenState.value?.copy(
                        loadingVisible = false,
                        paginationVisible = false,
                        errorMessage = result.toString()
                    )
                }

                isLoading = false
            }
        }
    }
}
