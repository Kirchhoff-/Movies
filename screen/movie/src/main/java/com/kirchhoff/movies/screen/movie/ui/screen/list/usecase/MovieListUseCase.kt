package com.kirchhoff.movies.screen.movie.ui.screen.list.usecase

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIMovie
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.mapper.IMovieListMapper
import com.kirchhoff.movies.screen.movie.ui.screen.list.repository.IMovieListRepository

internal interface IMovieListUseCase {
    suspend fun fetchByGenre(genre: String, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchByCountry(countryId: String, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchSimilarMovies(id: MovieId, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchByCompany(companyId: String, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchNowPlaying(page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchPopular(page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchTopRated(page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchUpcoming(page: Int): Result<UIPaginated<UIMovie>>
}

internal class MovieListUseCase(
    private val movieListRepository: IMovieListRepository,
    private val movieListMapper: IMovieListMapper
) : IMovieListUseCase {

    override suspend fun fetchByGenre(genre: String, page: Int): Result<UIPaginated<UIMovie>> = fetchMovies {
        movieListRepository.byGenre(
            genre = genre,
            page = page
        )
    }

    override suspend fun fetchByCountry(countryId: String, page: Int): Result<UIPaginated<UIMovie>> = fetchMovies {
        movieListRepository.byCountry(
            countryId = countryId,
            page = page
        )
    }

    override suspend fun fetchSimilarMovies(id: MovieId, page: Int): Result<UIPaginated<UIMovie>> = fetchMovies {
        movieListRepository.similar(
            id = id,
            page = page
        )
    }

    override suspend fun fetchByCompany(companyId: String, page: Int): Result<UIPaginated<UIMovie>> = fetchMovies {
        movieListRepository.byCompany(
            companyId = companyId,
            page = page
        )
    }

    override suspend fun fetchNowPlaying(page: Int): Result<UIPaginated<UIMovie>> =
        fetchMovies { movieListRepository.nowPlaying(page) }

    override suspend fun fetchPopular(page: Int): Result<UIPaginated<UIMovie>> =
        fetchMovies { movieListRepository.popular(page) }

    override suspend fun fetchTopRated(page: Int): Result<UIPaginated<UIMovie>> =
        fetchMovies { movieListRepository.topRated(page) }

    override suspend fun fetchUpcoming(page: Int): Result<UIPaginated<UIMovie>> =
        fetchMovies { movieListRepository.upcoming(page) }

    private suspend fun fetchMovies(request: suspend () -> RepositoryResult<NetworkPaginated<NetworkMovie>>): Result<UIPaginated<UIMovie>> =
        when (val response = request.invoke()) {
            is RepositoryResult.Success -> Result.success(movieListMapper.createMovieList(response.data))
            else -> Result.failure(Exception("Can't fetch the movies list"))
        }
}
