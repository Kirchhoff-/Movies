package com.kirchhoff.movies.screen.movie.ui.screen.list.usecase

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.mapper.IMovieListMapper
import com.kirchhoff.movies.screen.movie.ui.screen.list.repository.IMovieListRepository

internal interface IMovieListUseCase {
    suspend fun fetchByGenre(genre: String, page: Int): kotlin.Result<UIPaginated<UIMovie>>
    suspend fun fetchByCountry(countryId: String, page: Int): kotlin.Result<UIPaginated<UIMovie>>
    suspend fun fetchSimilarMovies(id: MovieId, page: Int): kotlin.Result<UIPaginated<UIMovie>>
    suspend fun fetchByCompany(companyId: String, page: Int): kotlin.Result<UIPaginated<UIMovie>>
    suspend fun fetchNowPlaying(page: Int): kotlin.Result<UIPaginated<UIMovie>>
    suspend fun fetchPopular(page: Int): kotlin.Result<UIPaginated<UIMovie>>
    suspend fun fetchTopRated(page: Int): kotlin.Result<UIPaginated<UIMovie>>
    suspend fun fetchUpcoming(page: Int): kotlin.Result<UIPaginated<UIMovie>>
}

internal class MovieListUseCase(
    private val movieListRepository: IMovieListRepository,
    private val movieListMapper: IMovieListMapper
) : IMovieListUseCase {

    override suspend fun fetchByGenre(genre: String, page: Int): kotlin.Result<UIPaginated<UIMovie>> = fetchMovies {
        movieListRepository.fetchByGenre(
            genre = genre,
            page = page
        )
    }

    override suspend fun fetchByCountry(countryId: String, page: Int): kotlin.Result<UIPaginated<UIMovie>> = fetchMovies {
        movieListRepository.fetchByCountry(
            countryId = countryId,
            page = page
        )
    }

    override suspend fun fetchSimilarMovies(id: MovieId, page: Int): kotlin.Result<UIPaginated<UIMovie>> = fetchMovies {
        movieListRepository.fetchSimilar(
            id = id,
            page = page
        )
    }

    override suspend fun fetchByCompany(companyId: String, page: Int): kotlin.Result<UIPaginated<UIMovie>> = fetchMovies {
        movieListRepository.fetchByCompany(
            companyId = companyId,
            page = page
        )
    }

    override suspend fun fetchNowPlaying(page: Int): kotlin.Result<UIPaginated<UIMovie>> =
        fetchMovies { movieListRepository.fetchNowPlaying(page) }

    override suspend fun fetchPopular(page: Int): kotlin.Result<UIPaginated<UIMovie>> =
        fetchMovies { movieListRepository.fetchPopular(page) }

    override suspend fun fetchTopRated(page: Int): kotlin.Result<UIPaginated<UIMovie>> =
        fetchMovies { movieListRepository.fetchTopRated(page) }

    override suspend fun fetchUpcoming(page: Int): kotlin.Result<UIPaginated<UIMovie>> =
        fetchMovies { movieListRepository.fetchUpcoming(page) }

    private suspend fun fetchMovies(request: suspend () -> Result<NetworkPaginated<NetworkMovie>>): kotlin.Result<UIPaginated<UIMovie>> =
        when (val response = request.invoke()) {
            is Result.Success -> kotlin.Result.success(movieListMapper.createMovieList(response.data))
            else -> kotlin.Result.failure(Exception("Can't fetch the movies list"))
        }
}
