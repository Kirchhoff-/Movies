package com.kirchhoff.movies.screen.movie.repository

import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.mapper.IDiscoverMapper
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.movie.data.UIMovieInfo
import com.kirchhoff.movies.screen.movie.data.UITrailersList
import com.kirchhoff.movies.screen.movie.mapper.details.IMovieDetailsMapper
import com.kirchhoff.movies.screen.movie.network.MovieService
import com.kirchhoff.movies.screen.movie.storage.IMovieImagesStorage
import com.kirchhoff.movies.storage.movie.IStorageMovie

internal class MovieRepository(
    private val movieService: MovieService,
    private val movieStorage: IStorageMovie,
    private val movieImagesStorage: IMovieImagesStorage,
    private val movieDetailsMapper: IMovieDetailsMapper,
    private val discoverMapper: IDiscoverMapper
) : BaseRepository(), IMovieRepository {

    override suspend fun fetchDiscoverList(page: Int): Result<UIPaginated<UIMovie>> {
        val result = apiCall { movieService.fetchDiscoverList(page) }

        if (result is Result.Success) {
            result.data.results.forEach { movieStorage.updateInfo(it) }
        }

        return discoverMapper.createUIDiscoverMovieList(result)
    }

    override suspend fun fetchDetails(movieId: Int): Result<UIMovieInfo> =
        movieDetailsMapper.createUIMovieDetails(
            apiCall {
                movieService.fetchDetails(movieId)
            }
        )

    override suspend fun fetchSimilarMovies(movieId: Int, page: Int): Result<UIPaginated<UIMovie>> =
        discoverMapper.createUIDiscoverMovieList(
            apiCall {
                movieService.fetchSimilarMovies(movieId, page)
            }
        )

    override suspend fun fetchByCountry(countryId: String, page: Int): Result<UIPaginated<UIMovie>> =
        discoverMapper.createUIDiscoverMovieList(
            apiCall {
                movieService.fetchByCountry(countryId, page)
            }
        )

    override suspend fun fetchTrailersList(movieId: Int): Result<UITrailersList> =
        movieDetailsMapper.createUITrailersList(
            apiCall {
                movieService.fetchTrailersList(movieId)
            }
        )

    override suspend fun fetchMovieCredits(movieId: Int): Result<UIEntertainmentCredits> =
        movieDetailsMapper.createUIEntertainmentCredits(
            apiCall {
                movieService.fetchMovieCredits(movieId)
            }
        )

    override suspend fun fetchByGenre(genre: String, page: Int): Result<UIPaginated<UIMovie>> =
        discoverMapper.createUIDiscoverMovieList(
            apiCall {
                movieService.fetchByGenre(genre, page)
            }
        )

    override suspend fun fetchImages(movieId: Int): Result<List<UIImage>> {
        val localImages = movieImagesStorage.fetchImages(movieId)

        return if (localImages != null) {
            Result.Success(localImages)
        } else {
            val result = movieDetailsMapper.createUIImages(
                apiCall {
                    movieService.fetchImages(movieId)
                }
            )

            if (result is Result.Success) {
                movieImagesStorage.updateImages(movieId, result.data)
            }

            result
        }
    }
}
