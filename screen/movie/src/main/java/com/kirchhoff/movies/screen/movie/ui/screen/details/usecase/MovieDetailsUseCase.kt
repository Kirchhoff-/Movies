package com.kirchhoff.movies.screen.movie.ui.screen.details.usecase

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.screen.movie.data.UIMovieInfo
import com.kirchhoff.movies.screen.movie.data.UITrailer
import com.kirchhoff.movies.screen.movie.mapper.IMovieDetailsMapper
import com.kirchhoff.movies.screen.movie.mapper.IMovieListMapper
import com.kirchhoff.movies.screen.movie.repository.IMovieDetailsRepository
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository

internal interface IMovieDetailsUseCase {
    suspend fun fetchDetails(id: MovieId): Result<UIMovieInfo>
    suspend fun fetchTrailersList(id: MovieId): Result<List<UITrailer>>
    suspend fun fetchMovieCredits(id: MovieId): Result<UIEntertainmentCredits>
    suspend fun fetchSimilarMovies(id: MovieId, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchImages(id: MovieId): Result<List<UIImage>>
}

internal class MovieDetailsUseCase(
    private val movieRepository: IMovieRepository,
    private val movieDetailsRepository: IMovieDetailsRepository,
    private val movieDetailsMapper: IMovieDetailsMapper,
    private val movieListMapper: IMovieListMapper
) : IMovieDetailsUseCase {

    override suspend fun fetchDetails(id: MovieId): Result<UIMovieInfo> =
        when (val response = movieDetailsRepository.fetchDetails(id)) {
            is Result.Success -> Result.Success(movieDetailsMapper.createUIMovieDetails(response.data))
            else -> response.mapErrorOrException()
        }

    override suspend fun fetchTrailersList(id: MovieId): Result<List<UITrailer>> =
        when (val response = movieDetailsRepository.fetchTrailersList(id)) {
            is Result.Success -> Result.Success(movieDetailsMapper.createUITrailersList(response.data))
            else -> response.mapErrorOrException()
        }

    override suspend fun fetchMovieCredits(id: MovieId): Result<UIEntertainmentCredits> =
        when (val response = movieDetailsRepository.fetchMovieCredits(id)) {
            is Result.Success -> {
                Result.Success(
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

            else -> response.mapErrorOrException()
        }

    override suspend fun fetchSimilarMovies(id: MovieId, page: Int): Result<UIPaginated<UIMovie>> =
        movieListMapper.createMovieList(movieDetailsRepository.fetchSimilarMovies(id, page))

    override suspend fun fetchImages(id: MovieId): Result<List<UIImage>> = movieRepository.fetchImages(id)
}
