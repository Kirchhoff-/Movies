package com.kirchhoff.movies.screen.movie.repository

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.movie.NetworkMovieDetails
import com.kirchhoff.movies.networkdata.details.movie.NetworkTrailersList
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.mapper.IMovieDetailsMapper
import com.kirchhoff.movies.screen.movie.mapper.IMovieListMapper
import com.kirchhoff.movies.screen.movie.network.MovieService
import com.kirchhoff.movies.screen.movie.storage.IMovieImagesStorage
import com.kirchhoff.movies.storage.movie.IStorageMovie

internal interface IMovieRepository {
    suspend fun fetchDiscoverList(page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchDetails(id: MovieId): Result<NetworkMovieDetails>
    suspend fun similarMovies(id: MovieId, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchSimilarMovies(id: MovieId, page: Int): Result<NetworkPaginated<NetworkMovie>>
    suspend fun fetchByCountry(countryId: String, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchByCompany(companyId: String, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchTrailersList(id: MovieId): Result<NetworkTrailersList>
    suspend fun fetchMovieCredits(id: MovieId): Result<NetworkEntertainmentCredits>
    suspend fun fetchByGenre(genre: String, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchImages(id: MovieId): Result<List<UIImage>>
}

internal class MovieRepository(
    private val movieService: MovieService,
    private val movieStorage: IStorageMovie,
    private val movieImagesStorage: IMovieImagesStorage,
    private val movieListMapper: IMovieListMapper,
    private val movieDetailsMapper: IMovieDetailsMapper
) : BaseRepository(), IMovieRepository {

    override suspend fun fetchDiscoverList(page: Int): Result<UIPaginated<UIMovie>> {
        val result = apiCall { movieService.fetchDiscoverList(page) }

        if (result is Result.Success) {
            result.data.results.forEach { movieStorage.updateInfo(it) }
        }

        return movieListMapper.createMovieList(result)
    }

    override suspend fun fetchDetails(id: MovieId): Result<NetworkMovieDetails> = apiCall { movieService.fetchDetails(id.value) }

    override suspend fun similarMovies(id: MovieId, page: Int): Result<UIPaginated<UIMovie>> =
        movieListMapper.createMovieList(
            apiCall {
                movieService.fetchSimilarMovies(id.value, page)
            }
        )

    override suspend fun fetchSimilarMovies(id: MovieId, page: Int): Result<NetworkPaginated<NetworkMovie>> =
        apiCall {
            movieService.fetchSimilarMovies(id.value, page)
        }

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

    override suspend fun fetchTrailersList(id: MovieId): Result<NetworkTrailersList> = apiCall { movieService.fetchTrailersList(id.value) }

    override suspend fun fetchMovieCredits(id: MovieId): Result<NetworkEntertainmentCredits> {
        val result = apiCall { movieService.fetchMovieCredits(id.value) }

        if (result is Result.Success) {
            movieStorage.updateCredits(id.value, result.data)
        }

        return result
    }

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
}
