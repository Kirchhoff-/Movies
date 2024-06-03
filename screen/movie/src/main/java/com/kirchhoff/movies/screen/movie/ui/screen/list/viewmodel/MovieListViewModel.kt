package com.kirchhoff.movies.screen.movie.ui.screen.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.utils.StringValue
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
            title = StringValue.Empty,
            errorMessage = "",
            loadingVisible = false,
            paginationVisible = false
        )
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

    private suspend fun fetchMovieList(): Result<UIPaginated<UIMovie>> = when (type) {
        is MovieListType.Genre -> movieRepository.fetchByGenre(type.genre.id, currentPage + 1)
        is MovieListType.Country -> movieRepository.fetchByCountry(type.country.id, currentPage + 1)
        is MovieListType.Similar -> movieRepository.similarMovies(type.movie.id, currentPage + 1)
        is MovieListType.Company -> movieRepository.fetchByCompany(type.company.id, currentPage + 1)
        MovieListType.NowPlaying -> movieRepository.fetchNowPlaying(currentPage + 1)
        MovieListType.Popular -> movieRepository.fetchPopular(currentPage + 1)
        MovieListType.TopRated -> movieRepository.fetchTopRated(currentPage + 1)
        MovieListType.Upcoming -> movieRepository.fetchUpcoming(currentPage + 1)
    }
}
