package com.kirchhoff.movies.screen.movie.ui.screen.genre.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UIGenre
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository
import com.kirchhoff.movies.screen.movie.ui.screen.genre.model.MovieListByGenreScreenState
import kotlinx.coroutines.launch

internal class MovieListByGenreViewModel(
    private val genre: UIGenre,
    private val movieRepository: IMovieRepository
) : ViewModel() {

    val screenState: MutableLiveData<MovieListByGenreScreenState> = MutableLiveData()

    private var currentPage: Int = 0
    private var totalPages: Int = Int.MAX_VALUE
    private var isLoading: Boolean = false

    init {
        screenState.value = MovieListByGenreScreenState(
            movieList = emptyList(),
            genre = genre.name,
            errorMessage = "",
            loadingVisible = false,
            paginationVisible = false
        )
    }

    fun loadMovieList() {
        viewModelScope.launch {
            if (currentPage < totalPages && !isLoading && screenState.value?.errorMessage?.isEmpty() == true) {
                isLoading = true

                val loadingVisible = currentPage == 0
                val paginationVisible = !loadingVisible
                screenState.value = screenState.value?.copy(
                    loadingVisible = loadingVisible,
                    paginationVisible = paginationVisible
                )

                when (val result = movieRepository.fetchByGenre(genre.id, currentPage + 1)) {
                    is Result.Success -> {
                        totalPages = result.data.totalPages
                        currentPage = result.data.page

                        val movieList = mutableListOf<UIMovie>().apply {
                            screenState.value?.let { this.addAll(it.movieList) }
                            addAll(result.data.results)
                        }

                        screenState.value = screenState.value?.copy(
                            movieList = movieList,
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
