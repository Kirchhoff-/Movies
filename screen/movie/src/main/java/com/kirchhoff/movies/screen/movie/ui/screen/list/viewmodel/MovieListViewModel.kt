package com.kirchhoff.movies.screen.movie.ui.screen.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository
import com.kirchhoff.movies.screen.movie.ui.screen.list.MovieListType
import com.kirchhoff.movies.screen.movie.ui.screen.list.model.MovieListScreenState
import kotlinx.coroutines.launch

internal class MovieListViewModel(
    private val type: MovieListType,
    private val movieRepository: IMovieRepository
) : ViewModel() {

    val screenState: MutableLiveData<MovieListScreenState> = MutableLiveData()

    private var currentPage: Int = 0
    private var totalPages: Int = Int.MAX_VALUE
    private var isLoading: Boolean = false

    init {
        screenState.value = MovieListScreenState(
            movieList = emptyList(),
            titleId = createTitleId(),
            titleArgs = createTitleArgs(),
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

                when (val result = fetchMovieList()) {
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

    private fun createTitleId(): Int = when (type) {
        is MovieListType.Genre -> R.string.movie_movies_with_genre_format
        is MovieListType.Country -> R.string.movie_movies_from_country_format
        is MovieListType.Similar -> R.string.movie_similar_to_format
    }

    private fun createTitleArgs(): Any = when (type) {
        is MovieListType.Genre -> type.genre.name
        is MovieListType.Country -> type.countryName
        is MovieListType.Similar -> type.movie.title.orEmpty()
    }

    private suspend fun fetchMovieList(): Result<UIPaginated<UIMovie>> = when (type) {
        is MovieListType.Genre -> movieRepository.fetchByGenre(type.genre.id, currentPage + 1)
        is MovieListType.Country -> movieRepository.fetchByCountry(type.countryId, currentPage + 1)
        is MovieListType.Similar -> movieRepository.fetchSimilarMovies(type.movie.id, currentPage + 1)
    }
}
