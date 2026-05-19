package com.kirchhoff.movies.screen.movie.ui.screen.details.usecase

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.ui.UIMovie
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.screen.movie.data.UIMovieInfo
import com.kirchhoff.movies.screen.movie.data.UITrailer
import com.kirchhoff.movies.screen.movie.mapper.IMovieDetailsMapper
import com.kirchhoff.movies.screen.movie.mapper.IMovieListMapper
import com.kirchhoff.movies.screen.movie.repository.IMovieDetailsRepository

internal interface IMovieDetailsUseCase {
    suspend fun fetchMovie(id: MovieId): Result<UIMovie>
    suspend fun fetchDetails(id: MovieId): Result<UIMovieInfo>
    suspend fun fetchTrailersList(id: MovieId): Result<List<UITrailer>>
    suspend fun fetchMovieCredits(id: MovieId): Result<UIEntertainmentCredits>
    suspend fun fetchSimilarMovies(id: MovieId, page: Int): Result<UIPaginated<UIMovie>>
}

internal class MovieDetailsUseCase(
    private val movieDetailsRepository: IMovieDetailsRepository,
    private val movieDetailsMapper: IMovieDetailsMapper,
    private val movieListMapper: IMovieListMapper
) : IMovieDetailsUseCase {

    override suspend fun fetchMovie(id: MovieId): Result<UIMovie> =
        when (val response = movieDetailsRepository.info(id)) {
            is RepositoryResult.Success -> Result.success(movieDetailsMapper.createUIMovie(response.data))
            else -> Result.failure(Exception("Can't fetch the movie info"))
        }

    override suspend fun fetchDetails(id: MovieId): Result<UIMovieInfo> =
        when (val response = movieDetailsRepository.details(id)) {
            is RepositoryResult.Success -> Result.success(movieDetailsMapper.createUIMovieDetails(response.data))
            else -> Result.failure(Exception("Can't fetch the details"))
        }

    override suspend fun fetchTrailersList(id: MovieId): Result<List<UITrailer>> =
        when (val response = movieDetailsRepository.trailersList(id)) {
            is RepositoryResult.Success -> Result.success(movieDetailsMapper.createUITrailersList(response.data))
            else -> Result.failure(Exception("Can't fetch the trailers list"))
        }

    override suspend fun fetchMovieCredits(id: MovieId): Result<UIEntertainmentCredits> =
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

    override suspend fun fetchSimilarMovies(id: MovieId, page: Int): Result<UIPaginated<UIMovie>> =
        when (val response = movieDetailsRepository.similarMovies(id, page)) {
            is RepositoryResult.Success -> Result.success(movieListMapper.createMovieList(response.data))
            else -> Result.failure(Exception("Can't fetch the similar movies"))
        }
}
