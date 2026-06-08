package com.kirchhoff.movies.screen.movie.ui.screen.details.usecase

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.ui.UIMovie
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.screen.movie.data.UIMovieInfo
import com.kirchhoff.movies.screen.movie.data.UITrailer
import com.kirchhoff.movies.screen.movie.mapper.MovieDetailsMapper
import com.kirchhoff.movies.screen.movie.mapper.MovieListMapper
import com.kirchhoff.movies.screen.movie.repository.MovieDetailsRepository

internal class MovieDetailsUseCase(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val movieListMapper: MovieListMapper
) {

    fun fetchMovie(id: MovieId): Result<UIMovie> =
        when (val response = movieDetailsRepository.info(id)) {
            is RepositoryResult.Success -> Result.success(movieDetailsMapper.createUIMovie(response.data))
            else -> Result.failure(Exception("Can't fetch the movie info"))
        }

    suspend fun fetchDetails(id: MovieId): Result<UIMovieInfo> =
        when (val response = movieDetailsRepository.details(id)) {
            is RepositoryResult.Success -> Result.success(movieDetailsMapper.createUIMovieDetails(response.data))
            else -> Result.failure(Exception("Can't fetch the details"))
        }

    suspend fun fetchTrailersList(id: MovieId): Result<List<UITrailer>> =
        when (val response = movieDetailsRepository.trailersList(id)) {
            is RepositoryResult.Success -> Result.success(movieDetailsMapper.createUITrailersList(response.data))
            else -> Result.failure(Exception("Can't fetch the trailers list"))
        }

    suspend fun fetchMovieCredits(id: MovieId): Result<UIEntertainmentCredits> =
        when (val response = movieDetailsRepository.movieCredits(id)) {
            is RepositoryResult.Success -> {
                Result.success(
                    movieDetailsMapper.createUIEntertainmentCredits(
                        NetworkEntertainmentCredits(
                            cast = response.data.cast?.sortedByDescending { it.popularity },
                            crew = response.data.crew
                                ?.sortedByDescending { it.popularity }
                                ?.distinctBy { it.name }
                        )
                    )
                )
            }

            else -> Result.failure(Exception("Can't fetch the movie credits"))
        }

    suspend fun fetchSimilarMovies(id: MovieId, page: Int): Result<UIPaginated<UIMovie>> =
        when (val response = movieDetailsRepository.similarMovies(id, page)) {
            is RepositoryResult.Success -> Result.success(movieListMapper.createMovieList(response.data))
            else -> Result.failure(Exception("Can't fetch the similar movies"))
        }
}
