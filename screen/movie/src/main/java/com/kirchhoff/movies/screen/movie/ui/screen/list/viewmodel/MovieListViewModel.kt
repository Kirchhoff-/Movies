package com.kirchhoff.movies.screen.movie.ui.screen.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.ui.screen.list.MovieListType
import com.kirchhoff.movies.screen.movie.ui.screen.list.model.MovieListScreenState
import com.kirchhoff.movies.screen.movie.ui.screen.list.usecase.IMovieListUseCase
import kotlinx.coroutines.launch

internal class MovieListViewModel(
    private val type: MovieListType,
    private val movieListUseCase: IMovieListUseCase
) : ViewModel() {

    val screenState: MutableLiveData<MovieListScreenState> = MutableLiveData()

    private var currentPage: Int = 0
    private var totalPages: Int = Int.MAX_VALUE
    private var isLoading: Boolean = false

    init {
        screenState.value = MovieListScreenState.Default
    }

    fun updateTitle() {
        val title = when (type) {
            is MovieListType.Genre -> StringValue.IdText(R.string.movie_movies_with_genre_format, type.genre.name)
            is MovieListType.Country -> StringValue.IdText(R.string.movie_movies_from_country_format, type.country.name)
            is MovieListType.Similar -> StringValue.IdText(R.string.movie_similar_to_format, type.movie.title)
            is MovieListType.Company -> StringValue.IdText(R.string.movie_movies_by_company_format, type.company.name)
            is MovieListType.NowPlaying -> StringValue.IdText(R.string.movie_now_playing)
            is MovieListType.Upcoming -> StringValue.IdText(R.string.movie_upcoming)
            is MovieListType.Popular -> StringValue.IdText(R.string.movie_popular)
            is MovieListType.TopRated -> StringValue.IdText(R.string.movie_top_rated)
        }

        screenState.value = screenState.value?.copy(title = title)
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

                fetchMovieList().fold(
                    onSuccess = { result ->
                        totalPages = result.totalPages
                        currentPage = result.page

                        val movieList = mutableListOf<UIMovie>().apply {
                            screenState.value?.let { this.addAll(it.movieList) }
                            addAll(result.results)
                        }

                        screenState.value = screenState.value?.copy(
                            movieList = movieList,
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

    private suspend fun fetchMovieList(): Result<UIPaginated<UIMovie>> = when (type) {
        is MovieListType.Genre -> movieListUseCase.fetchByGenre(type.genre.id, currentPage + 1)
        is MovieListType.Country -> movieListUseCase.fetchByCountry(type.country.id, currentPage + 1)
        is MovieListType.Similar -> movieListUseCase.fetchSimilarMovies(type.movie.id, currentPage + 1)
        is MovieListType.Company -> movieListUseCase.fetchByCompany(type.company.id, currentPage + 1)
        is MovieListType.NowPlaying -> movieListUseCase.fetchNowPlaying(currentPage + 1)
        is MovieListType.Popular -> movieListUseCase.fetchPopular(currentPage + 1)
        is MovieListType.TopRated -> movieListUseCase.fetchTopRated(currentPage + 1)
        is MovieListType.Upcoming -> movieListUseCase.fetchUpcoming(currentPage + 1)
    }
}
