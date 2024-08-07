package com.kirchhoff.movies.screen.movie.repository

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.movie.mapper.IMovieDetailsMapper
import com.kirchhoff.movies.screen.movie.mapper.IMovieListMapper
import com.kirchhoff.movies.screen.movie.network.MovieService
import com.kirchhoff.movies.screen.movie.storage.IMovieImagesStorage

internal interface IMovieRepository {
    suspend fun similarMovies(id: MovieId, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchByCountry(countryId: String, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchByCompany(companyId: String, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchByGenre(genre: String, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchNowPlaying(page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchUpcoming(page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchPopular(page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchTopRated(page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchImages(id: MovieId): Result<List<UIImage>>
}

internal class MovieRepository(
    private val movieService: MovieService,
    private val movieImagesStorage: IMovieImagesStorage,
    private val movieListMapper: IMovieListMapper,
    private val movieDetailsMapper: IMovieDetailsMapper
) : BaseRepository(), IMovieRepository {

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

    override suspend fun fetchNowPlaying(page: Int): Result<UIPaginated<UIMovie>> =
        movieListMapper.createMovieList(
            apiCall {
                movieService.fetchNowPlaying(page)
            }
        )

    override suspend fun fetchUpcoming(page: Int): Result<UIPaginated<UIMovie>> =
        movieListMapper.createMovieList(
            apiCall {
                movieService.fetchUpcoming(page)
            }
        )

    override suspend fun fetchPopular(page: Int): Result<UIPaginated<UIMovie>> =
        movieListMapper.createMovieList(
            apiCall {
                movieService.fetchPopular(page)
            }
        )

    override suspend fun fetchTopRated(page: Int): Result<UIPaginated<UIMovie>> =
        movieListMapper.createMovieList(
            apiCall {
                movieService.fetchTopRated(page)
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
}
