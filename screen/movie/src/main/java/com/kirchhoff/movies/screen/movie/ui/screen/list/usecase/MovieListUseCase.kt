package com.kirchhoff.movies.screen.movie.ui.screen.list.usecase

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIMovie
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.mapper.MovieListMapper
import com.kirchhoff.movies.screen.movie.ui.screen.list.repository.MovieListRepository

internal class MovieListUseCase(
    private val movieListRepository: MovieListRepository,
    private val movieListMapper: MovieListMapper
) {

    suspend fun fetchByGenre(genre: String, page: Int): Result<UIPaginated<UIMovie>> = fetchMovies {
        movieListRepository.byGenre(
            genre = genre,
            page = page
        )
    }

    suspend fun fetchByCountry(countryId: String, page: Int): Result<UIPaginated<UIMovie>> = fetchMovies {
        movieListRepository.byCountry(
            countryId = countryId,
            page = page
        )
    }

    suspend fun fetchSimilarMovies(id: MovieId, page: Int): Result<UIPaginated<UIMovie>> = fetchMovies {
        movieListRepository.similar(
            id = id,
            page = page
        )
    }

    suspend fun fetchByCompany(companyId: String, page: Int): Result<UIPaginated<UIMovie>> = fetchMovies {
        movieListRepository.byCompany(
            companyId = companyId,
            page = page
        )
    }

    suspend fun fetchNowPlaying(page: Int): Result<UIPaginated<UIMovie>> =
        fetchMovies { movieListRepository.nowPlaying(page) }

    suspend fun fetchPopular(page: Int): Result<UIPaginated<UIMovie>> =
        fetchMovies { movieListRepository.popular(page) }

    suspend fun fetchTopRated(page: Int): Result<UIPaginated<UIMovie>> =
        fetchMovies { movieListRepository.topRated(page) }

    suspend fun fetchUpcoming(page: Int): Result<UIPaginated<UIMovie>> =
        fetchMovies { movieListRepository.upcoming(page) }

    private suspend fun fetchMovies(request: suspend () -> RepositoryResult<NetworkPaginated<NetworkMovie>>): Result<UIPaginated<UIMovie>> =
        when (val response = request.invoke()) {
            is RepositoryResult.Success -> Result.success(movieListMapper.createMovieList(response.data))
            else -> Result.failure(Exception("Can't fetch the movies list"))
        }
}
