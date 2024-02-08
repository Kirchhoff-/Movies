package com.kirchhoff.movies.screen.tvshow.ui.screen.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.screen.tvshow.repository.ITvShowRepository
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.model.TvShowListScreenState
import kotlinx.coroutines.launch

internal class TvShowListViewModel(
    private val tvShowRepository: ITvShowRepository
) : ViewModel() {

    val screenState: MutableLiveData<TvShowListScreenState> = MutableLiveData()

    private var currentPage: Int = 1
    private var totalPages: Int = Int.MAX_VALUE
    private var isLoading: Boolean = false

    init {
        screenState.value = TvShowListScreenState(
            tvShowList = emptyList(),
            errorMessage = "",
            loadingVisible = false,
            paginationVisible = false,
            tvShowListVisible = false,
            emptyTextVisible = false
        )
    }

    fun loadTvShows() {
        viewModelScope.launch {
            if (currentPage < totalPages && !isLoading && screenState.value?.errorMessage?.isEmpty() == true) {
                isLoading = true

                val loadingVisible = currentPage == 1
                val paginationVisible = !loadingVisible
                screenState.value = screenState.value?.copy(
                    loadingVisible = loadingVisible,
                    paginationVisible = paginationVisible
                )

                when (val result = tvShowRepository.fetchDiscoverList(currentPage)) {
                    is Result.Success -> {
                        totalPages = result.data.totalPages
                        currentPage = result.data.page + 1

                        val tvShowList = mutableListOf<UITv>().apply {
                            screenState.value?.let { this.addAll(it.tvShowList) }
                            addAll(result.data.results)
                        }

                        screenState.value = screenState.value?.copy(
                            tvShowList = tvShowList,
                            loadingVisible = false,
                            paginationVisible = false,
                            errorMessage = "",
                            tvShowListVisible = tvShowList.isNotEmpty(),
                            emptyTextVisible = tvShowList.isEmpty()
                        )
                    }
                    else -> screenState.value = screenState.value?.copy(
                        loadingVisible = false,
                        paginationVisible = false,
                        errorMessage = result.toString(),
                        tvShowListVisible = screenState.value?.tvShowList?.isNotEmpty() == true,
                        emptyTextVisible = false
                    )
                }

                isLoading = false
            }
        }
    }
}
