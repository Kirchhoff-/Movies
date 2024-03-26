package com.kirchhoff.movies.screen.movie.repository

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.mapper.IMovieDetailsMapper
import com.kirchhoff.movies.screen.movie.mapper.IMovieListMapper
import com.kirchhoff.movies.screen.movie.network.MovieService
import com.kirchhoff.movies.screen.movie.storage.IMovieImagesStorage
import com.kirchhoff.movies.storage.movie.IStorageMovie
import retrofit2.Response

internal interface IMovieRepository {
    suspend fun fetchDiscoverList(page: Int): Result<UIPaginated<UIMovie>>
    suspend fun similarMovies(id: MovieId, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchByCountry(countryId: String, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchByCompany(companyId: String, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchByGenre(genre: String, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchImages(id: MovieId): Result<List<UIImage>>
    suspend fun fetchNowPlaying(page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchPopular(page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchTopRated(page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchUpcoming(page: Int): Result<UIPaginated<UIMovie>>
}

internal class MovieRepository(
    private val movieService: MovieService,
    private val movieStorage: IStorageMovie,
    private val movieImagesStorage: IMovieImagesStorage,
    private val movieListMapper: IMovieListMapper,
    private val movieDetailsMapper: IMovieDetailsMapper
) : BaseRepository(), IMovieRepository {

    override suspend fun fetchDiscoverList(page: Int): Result<UIPaginated<UIMovie>> = fetchMovies { movieService.fetchDiscoverList(page) }

    override suspend fun similarMovies(id: MovieId, page: Int): Result<UIPaginated<UIMovie>> =
        movieListMapper.createMovieList(
            apiCall {
                movieService.fetchSimilarMovies(id.value, page)
            }
        )

    override suspend fun fetchByCountry(countryId: String, page: Int): Result<UIPaginated<UIMovie>> =
        movieListMapper.createMovieList(
            apiCall {
                movieService.fetchByCountry(countryId, page)
            }
        )

    override suspend fun fetchByCompany(companyId: String, page: Int): Result<UIPaginated<UIMovie>> =
        movieListMapper.createMovieList(
            apiCall {
                movieService.fetchByCompany(companyId, page)
            }
        )

    override suspend fun fetchByGenre(genre: String, page: Int): Result<UIPaginated<UIMovie>> =
        movieListMapper.createMovieList(
            apiCall {
                movieService.fetchByGenre(genre, page)
            }
        )

    override suspend fun fetchImages(id: MovieId): Result<List<UIImage>> {
        val localImages = movieImagesStorage.fetchImages(id)

        return if (localImages != null) {
            Result.Success(localImages)
        } else {
            val result = movieDetailsMapper.createUIImages(
                apiCall {
                    movieService.fetchImages(id.value)
                }
            )

            if (result is Result.Success) {
                movieImagesStorage.updateImages(id, result.data)
            }

            result
        }
    }

    override suspend fun fetchNowPlaying(page: Int): Result<UIPaginated<UIMovie>> = fetchMovies { movieService.fetchNowPlaying(page) }

    override suspend fun fetchPopular(page: Int): Result<UIPaginated<UIMovie>> = fetchMovies { movieService.fetchPopular(page) }

    override suspend fun fetchTopRated(page: Int): Result<UIPaginated<UIMovie>> = fetchMovies { movieService.fetchTopRated(page) }

    override suspend fun fetchUpcoming(page: Int): Result<UIPaginated<UIMovie>> = fetchMovies { movieService.fetchUpcoming(page) }

    private suspend fun fetchMovies(call: suspend () -> Response<NetworkPaginated<NetworkMovie>>): Result<UIPaginated<UIMovie>> {
        val result = apiCall { call.invoke() }

        if (result is Result.Success) {
            result.data.results.forEach { movieStorage.updateInfo(it) }
        }

        return movieListMapper.createMovieList(result)
    }
}
